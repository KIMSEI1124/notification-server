plugins {
    id("java")
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.epages.restdocs-api-spec") version "0.18.4"
    id("jacoco")
}

val projectVersion = "0.0.1"
group = "com.wypl"
version = projectVersion

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

jacoco {
    toolVersion = "0.8.11"
}

val snippetsDir by extra { file("build/generated-snippets") }

repositories {
    mavenCentral()
}

dependencies {
    /* Spring Boot */
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo.spring31x:4.11.0")

    /* Database */
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

    /* Kafka */
    implementation("org.springframework.kafka:spring-kafka")
    testImplementation("org.springframework.kafka:spring-kafka-test:3.2.2")

    /* Test */
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("io.projectreactor:reactor-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    /* API Docs */
    testImplementation("com.epages:restdocs-api-spec-mockmvc:0.18.4")

    /* ETC */
    implementation("io.github.oshai:kotlin-logging-jvm:5.1.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

/* Jacoco Start */
tasks.withType<JacocoReport> {
    reports {
        html.required.set(true)
        html.outputLocation.set(file("reports/jacoco/index.xml"))
    }

    classDirectories.setFrom(
        files(classDirectories.files.map {
            fileTree(it) {
                exclude(
                    "**/*Application*",
                    "**/*Request*",
                    "**/*Response*",
                    "**/annotation/**",
                    "**/exception/**",
                    "**/global/**",
                    "**/message/**",
                    "**/properties/**"
                )
            }
        })
    )
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            enabled = true
            element = "CLASS"

            limit {
                counter = "LINE"
                value = "COVEREDRATIO"
                minimum = "0.00".toBigDecimal()
            }

            excludes = listOf(
                "**/*Application*",
                "**/*Request*",
                "**/*Response*",
                "**/annotation/**",
                "**/exception/**",
                "**/global/**",
                "**/message/**",
                "**/properties/**"
            )
        }
    }
}
/* Jacoco End */

/* API Docs Start */
openapi3 {
    this.setServer("http://127.0.0.1:8080")
    title = "What's Your Plan! Notification Server API Docs"
    description = "What's Your Plan! Notification Server API Description"
    version = projectVersion
    format = "yaml"
}

tasks.register<Copy>("copyOasToSwagger") {
    description = "생성된 API 문서를 Static 폴더로 복사합니다."
    group = JavaBasePlugin.DOCUMENTATION_GROUP

    dependsOn(tasks.named("openapi3"))

    delete(file("src/main/resources/static/swagger-ui/openapi3.yaml"))
    from("build/api-spec/openapi3.yaml")
    into("src/main/resources/static/swagger-ui/")
}
/* API Docs End */