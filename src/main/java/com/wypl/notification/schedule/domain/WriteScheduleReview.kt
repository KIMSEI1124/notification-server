package com.wypl.notification.schedule.domain

import com.wypl.notification.global.mongodb.MongoBaseEntity
import com.wypl.notification.schedule.event.WriteScheduleReviewEvent
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDateTime
import java.util.*

@Document(collection = "write_schedule_review")
class WriteScheduleReview(
    recipientId: Long,
    nickname: String,
    scheduleId: Long,
    scheduleTitle: String,
    override var createdAt: LocalDateTime? = null,
    override var updatedAt: LocalDateTime? = null,
) : MongoBaseEntity() {

    companion object {
        fun from(writeScheduleReviewEvent: WriteScheduleReviewEvent): WriteScheduleReview {
            return WriteScheduleReview(
                recipientId = writeScheduleReviewEvent.recipientId,
                nickname = writeScheduleReviewEvent.nickname,
                scheduleId = writeScheduleReviewEvent.scheduleId,
                scheduleTitle = writeScheduleReviewEvent.scheduleTitle,
            )
        }
    }

    @Id
    @Field(name = "write_schedule_review_id")
    var id: UUID = UUID.randomUUID()

    @Field(name = "recipient_id")
    val recipientId: Long = recipientId

    @Field(name = "nickname")
    val nickname: String = nickname

    @Field(name = "schedule_id")
    val scheduleId: Long = scheduleId

    @Field(name = "schedule_title")
    val scheduleTitle: String = scheduleTitle
}