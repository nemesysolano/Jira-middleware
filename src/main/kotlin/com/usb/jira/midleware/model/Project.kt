package com.usb.jira.midleware.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.apache.commons.lang3.StringUtils

data class Project(
    val id: String = StringUtils.EMPTY,
    val key: String = StringUtils.EMPTY,
    val name: String = StringUtils.EMPTY,
    val projectTypeKey: String = StringUtils.EMPTY,
    val issueTypes: List<IssueType> = emptyList()
) {
    companion object {
        val Empty = Project(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, emptyList())
    }
}
