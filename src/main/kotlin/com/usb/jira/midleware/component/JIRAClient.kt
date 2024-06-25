package com.usb.jira.midleware.component

import com.usb.jira.midleware.component.response.UserList
import com.usb.jira.midleware.model.Issue
import com.usb.jira.midleware.model.Project
import com.usb.jira.midleware.model.User
import com.usb.jira.midleware.model.request.CreateIssueRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.nio.charset.StandardCharsets
import java.util.Base64

@Component
class JIRAClient @Autowired constructor(
    @Value("\${jira.domain}") private val domain: String,
    @Value("\${jira.username}") private val username: String,
    @Value("\${jira.password}") private val password: String
) : RestTemplate() {

    private fun createHeaders(): HttpHeaders {
        return HttpHeaders().apply {
            val auth = "$username:$password"
            val encodedAuth = Base64.getEncoder().encodeToString(auth.toByteArray(StandardCharsets.UTF_8))
            val authHeader = "Basic $encodedAuth"
            set("Authorization", authHeader)
        }
    }

    fun getIssue(key: String): Issue {
        val headers = createHeaders()
        val entity = HttpEntity<String>(headers)
        val response = exchange("$domain/rest/api/2/issue/$key", HttpMethod.GET, entity, Issue::class.java)
        return response.body ?: Issue.Empty
    }

    fun getUserByEmail(email: String): User {
        val headers = createHeaders()
        val entity = HttpEntity<String>(headers)
        val response = exchange("$domain/rest/api/2/users/search", HttpMethod.GET, entity, UserList::class.java)
        return response.body?.find { it.emailAddress == email } ?: User.Empty
    }

    fun getProjectById(id: String): Project {
        val headers = createHeaders()
        val entity = HttpEntity<String>(headers)
        val response = exchange("$domain/rest/api/2/project/$id", HttpMethod.GET, entity, Project::class.java)
        return response.body ?: Project.Empty
    }

    fun createIssue(createIssueRequest: CreateIssueRequest): Issue {

        val headers = createHeaders().apply {this.set("Content-Type", "application/json")}
        val entity = HttpEntity(createIssueRequest.toJiraRequestString(), headers)
        val response = exchange("$domain/rest/api/2/issue", HttpMethod.POST, entity, Issue::class.java)
        return response.body ?: Issue.Empty
    }
}