import io.gatling.javaapi.core.*
import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.http.*
import io.gatling.javaapi.http.HttpDsl.*

class TestSimulation : Simulation() {
    val httpProtocol =
        http
            .baseUrl("http://localhost:8080")
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
        scenario("External API Call Coroutine Test").exec(
            HttpDsl
                .http("Blocking Thread")
                .get("/non-blocking1")
                .check(CoreDsl.jsonPath("$.data").`is`("Complete"))
                .check(HttpDsl.status().`is`(200)),
        )
    val coroutineScn =
        scenario("External API Call @Async Test").exec(
            HttpDsl
                .http("Blocking Thread")
                .get("/non-blocking2")
                .check(CoreDsl.jsonPath("$.data").`is`("Complete"))
                .check(HttpDsl.status().`is`(200)),
        )
    val coroutineScn2 =
        scenario("External API Call Coroutine Test2").exec(
            HttpDsl
                .http("Blocking Thread")
                .get("/non-blocking1-1")
                .check(CoreDsl.jsonPath("$.data").`is`("Complete, string value"))
                .check(HttpDsl.status().`is`(200)),
        )

    init {
        setUp(
            blockingScn.injectOpen(
//                constantUsersPerSec(1_000.toDouble()).during(60),
//                rampUsers(10_000).during(30),
//                stressPeakUsers(100).during(5),
            ),
            asyncScn.injectOpen(
//                constantUsersPerSec(1_000.toDouble()).during(60),
//                atOnceUsers(10_000),
//                rampUsers(10_000).during(30),
//                stressPeakUsers(100).during(2),
            ),
            coroutineScn.injectOpen(
//                constantUsersPerSec(1_000.toDouble()).during(60),
//                atOnceUsers(10_000),
//                rampUsers(10_000).during(30),
//                stressPeakUsers(100).during(2),
            ),
            coroutineScn2.injectOpen(
//                constantUsersPerSec(1_000.toDouble()).during(60),
//                atOnceUsers(10_000),
                rampUsers(10_000).during(30),
//                stressPeakUsers(100).during(2),
            ),
        ).protocols(httpProtocol)
    }
}
