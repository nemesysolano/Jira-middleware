package com.usb.jira.midleware.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.apache.commons.lang3.StringUtils

data class User(
    val self: String,
    val accountId: String,
    val displayName: String,
    val active: Boolean,
    val emailAddress: String = StringUtils.EMPTY,
    val accountType: String = StringUtils.EMPTY,
) {
    companion object {
        val Empty = User(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, false,  StringUtils.EMPTY, StringUtils.EMPTY)
    }
}
