package com.wypl.notification.schedule.repository

import com.wypl.notification.schedule.fixture.WriteScheduleReviewFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest

@DataMongoTest
class ScheduleReviewRepositoryTest(
    @Autowired val repository: ScheduleReviewRepository
) {
    @DisplayName("일정 리뷰 작성 요청 이벤트 저장에 성공한다.")
    @Test
    fun saveSuccessTest() {
        /* Given */
        val writeScheduleReview = WriteScheduleReviewFixture.김세이_CS스터디_일정.toEntity()

        /* When */
        val savedWriteScheduleReview = repository.save(writeScheduleReview)

        /* Then */
        assertThat(savedWriteScheduleReview).isEqualTo(writeScheduleReview)
        assertThat(savedWriteScheduleReview.id).isNotNull()
    }
}