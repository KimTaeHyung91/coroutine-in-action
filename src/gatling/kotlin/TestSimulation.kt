import io.gatling.javaapi.core.*
import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.http.*
import io.gatling.javaapi.http.HttpDsl.*

class TestSimulation : Simulation() {
    val httpProtocol =
        http
            .baseUrl("http://app:8080")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json")

    val blockingScn =
        scenario("External API Call Blocking Thread Test").exec(
            HttpDsl
                .http("Blocking Thread")
                .get("/blocking")
                .check(CoreDsl.jsonPath("$.data").`is`("Complete"))
                .check(HttpDsl.status().`is`(200)),
        )
    val asyncScn =
        scenario("External API Call @Async Test").exec(
            HttpDsl
                .http("Blocking Thread")
                .get("/non-blocking1")
                .check(CoreDsl.jsonPath("$.data").`is`("Complete"))
                .check(HttpDsl.status().`is`(200)),
        )
    val coroutineScn =
        scenario("External API Call Coroutine Test").exec(
            HttpDsl
                .http("Blocking Thread")
                .get("/non-blocking2")
                .check(CoreDsl.jsonPath("$.data").`is`("Complete"))
                .check(HttpDsl.status().`is`(200)),
        )

    init {
        setUp(
            blockingScn.injectOpen(rampUsers(1_000).during(10)),
            asyncScn.injectOpen(rampUsers(1_000).during(10)),
            coroutineScn.injectOpen(rampUsers(1_000).during(10)),
        ).protocols(httpProtocol)
    }
}
