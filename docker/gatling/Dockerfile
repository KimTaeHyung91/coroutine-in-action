FROM eclipse-temurin:21.0.4_7-jre-alpine

COPY ../../gradlew .
COPY ../../gradle gradle
COPY ../../build.gradle.kts ./
COPY ../../settings.gradle.kts ./
COPY ../../src/gatling src/gatling

RUN chmod +x ./gradlew

ENTRYPOINT["gradlew", "gatlingRun", "-Dgatling.simulationClass=gatling.kotlin.TestSimulation"]