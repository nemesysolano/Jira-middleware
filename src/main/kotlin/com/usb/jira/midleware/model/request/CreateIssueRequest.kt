package com.usb.jira.midleware.model.request


import com.usb.jira.midleware.model.Project

data class CreateIssueRequest(
    val project: Project,
    val issueType: String,
    val summary: String,
    val externalToken: String,
    val userId: String,
    val issueLink: String,
    val riskLevel: Int
) {
    fun toJiraRequestString() : String = StringBuilder(javaClass.getResource("/com/usb/jira/midleware/model/request/CreateIssueRequestTemplate.json")!!.readText())
        .replace("\\{projectId\\}".toRegex(), project.id)
        .replace("\\{issueType\\}".toRegex(), issueType)
        .replace("\\{summary\\}".toRegex(), summary)
        .replace("\\{externalToken\\}".toRegex(), externalToken)
        .replace("\\{userId\\}".toRegex(), userId)
        .replace("\\{issueLink\\}".toRegex(), issueLink)
        .replace("\\{riskLevel\\}".toRegex(), riskLevel.toString())
        .toString()
}
