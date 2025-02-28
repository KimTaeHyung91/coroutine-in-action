package com.example.coroutinesample

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

private val logger = KotlinLogging.logger("main")

@SpringBootApplication
class CoroutineSampleApplication

fun main(args: Array<String>) {
    runApplication<CoroutineSampleApplication>(*args)
    logger.info { "main Thread Name: ${Thread.currentThread().name}" }
}
