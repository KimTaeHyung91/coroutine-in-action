package com.example.coroutinesample

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

@Service
class SampleService {
    private val logger = KotlinLogging.logger(SampleService::class.java.name)

    fun doSomething1(value: String) {
        logger.info { "[${Thread.currentThread().name}] $value :: save to something in doSomething1" }
    }

    suspend fun doSomething2(value: String) {
        logger.info { "[${Thread.currentThread().name}] $value :: save to something in doSomething2" }
    }
}
