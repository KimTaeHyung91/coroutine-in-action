FROM eclipse-temurin:21.0.4_7-jre-alpine as build
WORKDIR /builder

COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts ./
COPY settings.gradle.kts ./
COPY src src

RUN chmod +x ./gradlew

RUN ./gradlew bootJar

FROM eclipse-temurin:21.0.4_7-jre-alpine as extract
WORKDIR /extract

COPY --from=build /builder/build/libs/application.jar application.jar
RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted


FROM eclipse-temurin:21.0.4_7-jre-alpine
WORKDIR /app

ARG profile_active
RUN echo "spring profile: $profile_active in docker build"
COPY --from=extract /extract/application.jar application.jar
COPY --from=extract /extract/extracted/dependencies/ ./
COPY --from=extract /extract/extracted/spring-boot-loader/ ./
COPY --from=extract /extract/extracted/snapshot-dependencies/ ./
COPY --from=extract /extract/extracted/application/ ./

ENTRYPOINT ["java", "-jar", "application.jar", "--spring.profiles.active=$profile_active"]
