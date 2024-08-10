package com.wypl.notification.group

import com.wypl.notification.emitter.kafka.NotificationKafkaProducer
import com.wypl.notification.group.event.GroupInviteEvent
import com.wypl.notification.schedule.event.ScheduleKafkaConsumerEventType
import com.wypl.notification.schedule.event.WriteScheduleReviewEvent
import com.wypl.notification.schedule.repository.ScheduleReviewRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka

@SpringBootTest
@EmbeddedKafka(
    partitions = 1,
    brokerProperties = ["listeners=PLAINTEXT://localhost:9092"],
    ports = [9092]
)
class GroupKafkaConsumerTest(
    @Autowired val kafkaProducer: NotificationKafkaProducer,
    @Autowired val writeScheduleReviewRepository: ScheduleReviewRepository
) {
    @Test
    fun groupInviteConsumerTest() {
        val message = GroupInviteEvent(
            senderId = 1,
            recipientId = 2,
            nickname = "김세이",
            groupId = 1,
            groupName = "What's Your Plan!"
        );

        val event = WriteScheduleReviewEvent(
            recipientId = 1,
            nickname = "김세이",
            scheduleId = 1,
            scheduleTitle = "CS 스터디"
        )

        kafkaProducer.send("schedule", ScheduleKafkaConsumerEventType.WRITE_REVIEW.toString(), event)

        Thread.sleep(2000)

        val findAll = writeScheduleReviewRepository.findAll()
        println("##### FIND ALL : $findAll")
        assertThat(findAll).hasSize(1)
    }
}