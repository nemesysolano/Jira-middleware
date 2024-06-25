package com.usb.jira.midleware

import com.github.javafaker.Faker
import com.usb.jira.midleware.component.JIRAClient
import com.usb.jira.midleware.model.Issue
import com.usb.jira.midleware.model.Project
import com.usb.jira.midleware.model.User
import com.usb.jira.midleware.model.request.CreateIssueRequest
import org.apache.commons.lang3.RandomUtils
import org.apache.commons.lang3.StringUtils
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JIRAClientTest {
    private val log = LoggerFactory.getLogger(JIRAClientTest::class.java)
    @Autowired
    lateinit var jiraClient: JIRAClient

    @Value("\${jira.test.issueId}")
    lateinit var testIssueId: String

    @Value("\${jira.test.email}")
    lateinit var testEmail: String

    @Value("\${jira.test.projectId}")
    lateinit var testProjectId: String

    @Value("\${jira.test.issueType}")
    lateinit var issueType: String

    @Value("\${jira.test.externalToken}")
    lateinit var externalToken: String

    @Test
    fun getIssueTest() {
        val issue = jiraClient.getIssue(testIssueId)
        assertTrue(issue != Issue.Empty)
        assertTrue(issue.fields.project!= Project.Empty)
        assertTrue(issue.fields.assignee != User.Empty)
        assertTrue(issue.fields.parent != Issue.Empty)
    }

    @Test
    fun getUserByEmailTest() {
        val user = jiraClient.getUserByEmail(testEmail)
        assertTrue(user != User.Empty)
        assertTrue(user.emailAddress == testEmail)
    }

    @Test
    fun getProjectByIdTest() {
        val project = jiraClient.getProjectById(testProjectId)
        assertTrue(project != Project.Empty)
        assertTrue(project.id == testProjectId)
        assertTrue(project.issueTypes.isNotEmpty())

        log.debug(project.issueTypes.toString())
    }

    @Test
    fun createIssueTest() {
        val faker = Faker()
        val user = jiraClient.getUserByEmail(testEmail)
        jiraClient.createIssue(
            CreateIssueRequest(
                Project(id = testProjectId, key = StringUtils.EMPTY),
                issueType,
                faker.lorem().sentence(),
                externalToken,
                user.accountId,
                faker.internet().url(),
                RandomUtils.nextInt(1, 5)
            )
        )
    }

}