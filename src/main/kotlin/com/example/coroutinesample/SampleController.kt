package com.example.coroutinesample

import kotlinx.coroutines.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController(
    private val sampleService: SampleService,
) {
    @GetMapping("/blocking")
    fun test(): Response {
        sampleService.doSomething1("string value")

        return Response("Complete")
    }

    @GetMapping("/non-blocking1")
    fun test2(): Response {
        val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

        val str1 = scope.async { sampleService.doSomething2("string value") }

        runBlocking { str1.await() }

        return Response("Complete")
    }

    @GetMapping("/non-blocking2")
    fun test3(): Response {
        sampleService.doSomething3("string value")
        return Response("Complete")
    }

    data class Response(
        val data: String,
    )
}
