package com.example.coroutinesample

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.delay
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class SampleService {
    private val logger = KotlinLogging.logger(SampleService::class.java.name)

    fun doSomething1(value: String) {
        Thread.sleep(250)
        logger.info { "[${Thread.currentThread().name}] $value :: save to something in doSomething1" }
    }

    suspend fun doSomething2(value: String) {
        delay(250)
        logger.info { "[${Thread.currentThread().name}] $value :: save to something in doSomething2" }
    }

    @Async
    fun doSomething3(value: String) {
        Thread.sleep(250)
        logger.info { "[${Thread.currentThread().name}] $value :: save to something in doSomething3" }
    }
}
