package com.wypl.notification.schedule.repository

import com.wypl.notification.schedule.domain.WriteScheduleReview
import org.springframework.data.mongodb.repository.MongoRepository

interface ScheduleReviewRepository : MongoRepository<WriteScheduleReview, String>