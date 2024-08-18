package com.wypl.notification.global.mongodb

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDateTime

abstract class MongoBaseEntity {
    @get: CreatedDate
    @get:Field(name = "created_at")
    abstract var createdAt: LocalDateTime?

    @get: LastModifiedDate
    @get:Field(name = "updated_at")
    abstract var updatedAt: LocalDateTime?
}