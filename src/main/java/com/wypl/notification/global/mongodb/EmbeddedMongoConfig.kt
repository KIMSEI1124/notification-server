package com.wypl.notification.global.mongodb

import de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("dev", "prod")
@EnableAutoConfiguration(exclude = [EmbeddedMongoAutoConfiguration::class])
@Configuration
class EmbeddedMongoConfig {
}