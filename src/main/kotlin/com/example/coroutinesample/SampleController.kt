package com.example.coroutinesample

import kotlinx.coroutines.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.function.ServerResponse.async

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

        scope.launch {
            sampleService.doSomething2("string value")
        }

        return Response("Complete")
    }

    @GetMapping("/non-blocking1-1")
    fun test4(): Response {
        val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        val async = scope.async { sampleService.doSomething4("string value") }
        return runBlocking(Dispatchers.IO) { Response(async.await()) }
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
