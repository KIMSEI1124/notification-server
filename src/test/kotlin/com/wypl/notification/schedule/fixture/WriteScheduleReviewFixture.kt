package com.wypl.notification.schedule.fixture

import com.wypl.notification.schedule.domain.WriteScheduleReview
import com.wypl.notification.schedule.event.WriteScheduleReviewEvent

enum class WriteScheduleReviewFixture(
    private val recipientId: Long,
    private val nickname: String,
    private val scheduleTitle: String,
    private val scheduleId: Long
) {
    김세이_CS스터디_일정(1, "김세이", "CS 스터디", 1);

    fun toEvent(): WriteScheduleReviewEvent {
        return WriteScheduleReviewEvent(
            recipientId, nickname, scheduleId, scheduleTitle
        )
    }

    fun toEntity(): WriteScheduleReview {
        return WriteScheduleReview.from(toEvent())
    }
}