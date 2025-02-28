package com.example.coroutinesample

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController(private val sampleService: SampleService) {
    @GetMapping("/blocking")
    fun test(): String {
        for (i in 1..100) {
            sampleService.doSomething1("doSomething$i")
            Thread.sleep(250) // 외부 api1 조회

            sampleService.doSomething1("doSomething${i + 1}")
            Thread.sleep(250) // 외부 api2 조회
        }

        return "Complete"
    }

    @GetMapping("/non-blocking")
    fun test2(): String {
        val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

        val list = arrayListOf<Deferred<Unit>>()
        for (i in 1..100) {
            val str1 =
                scope.async {
                    sampleService.doSomething2("doSomething$i")
                    delay(250)
                }

            val str2 =
                scope.async {
                    sampleService.doSomething2("doSomething${i + 1}")
                    delay(250)
                }

            list.add(str1)
            list.add(str2)
        }

        return runBlocking {
            list.awaitAll()
            "Complete"
        }
    }
}
