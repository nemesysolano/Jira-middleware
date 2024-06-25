package com.usb.jira.midleware.model

import org.apache.commons.lang3.StringUtils

data class IssueFields(
    val project: Project = Project.Empty,
    val lastViewed: String = StringUtils.EMPTY,
    val created: String = StringUtils.EMPTY,
    val assignee: User = User.Empty,
    val parent: Issue? = null,
) {
    companion object {
        val Empty = IssueFields(Project.Empty, StringUtils.EMPTY, StringUtils.EMPTY, User.Empty, null)
    }
}
