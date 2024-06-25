package com.usb.jira.midleware

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MidlewareApplication

fun main(args: Array<String>) {
	runApplication<MidlewareApplication>(*args)
}
