package com.example.coroutinesample

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

private val logger = KotlinLogging.logger("main")

@SpringBootApplication
@EnableAsync
class CoroutineSampleApplication

fun main(args: Array<String>) {
    runApplication<CoroutineSampleApplication>(*args)
    logger.info { "main Thread Name: ${Thread.currentThread().name}" }
}
