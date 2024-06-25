package com.usb.jira.midleware.model

import org.apache.commons.lang3.StringUtils

data class IssueType(
    val self: String,
    val id: String,
    val description: String,
    val iconUrl: String,
    val name: String,
    val subtask: Boolean,
    val avatarId: Int,
) {
    companion object {
        val Empty = IssueType(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, false, 0)
    }
}