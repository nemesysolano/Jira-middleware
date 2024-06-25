package com.usb.jira.midleware.model

import org.apache.commons.lang3.StringUtils


data class Issue(
    val expand: String = StringUtils.EMPTY,
    val id: String = StringUtils.EMPTY,
    val key: String = StringUtils.EMPTY,
    val fields: IssueFields = IssueFields.Empty,
) {
    companion object {
        val Empty = Issue(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, IssueFields.Empty)
    }
}