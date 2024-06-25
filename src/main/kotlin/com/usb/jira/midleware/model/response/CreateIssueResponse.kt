package com.usb.jira.midleware.model.response

data class CreateIssueResponse(
    val id: String,
    val self: String,
    val key: String
)