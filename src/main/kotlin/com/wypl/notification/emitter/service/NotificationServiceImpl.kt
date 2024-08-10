package com.wypl.notification.emitter.service

import com.wypl.notification.emitter.message.NotificationSendable
import com.wypl.notification.emitter.message.SubscribeMessage
import com.wypl.notification.emitter.repository.SseEmitterRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.io.IOException

private val logger = KotlinLogging.logger {}

@Service
class NotificationServiceImpl(
    private val sseEmitterRepository: SseEmitterRepository
) : EmitterSubscribeService {
    companion object {
        const val TIME_OUT_MILLISECONDS: Long = 60 * 60 * 1_000L
    }

    override fun connections(memberId: Int): SseEmitter {
        val sseEmitter = SseEmitter(TIME_OUT_MILLISECONDS)
        sseEmitterRepository.save(memberId, sseEmitter)

        sseEmitterCallback(sseEmitter, memberId)

        return sendMessage(
            message = SubscribeMessage(
                memberId = memberId,
                timeout = TIME_OUT_MILLISECONDS
            ),
            memberId,
        )
    }

    private fun sseEmitterCallback(
        sseEmitter: SseEmitter,
        memberId: Int
    ) {
        sseEmitter.onCompletion {
            logger.debug { "\n\n${memberId}번 사용자의 연결이 만료되어 삭제합니다.\n" }
            sseEmitterRepository.deleteById(memberId)
        }
        sseEmitter.onTimeout {
            logger.debug { "\n\n${memberId}번 사용자의 연결 요청이 Timeout으로 인해 종료합니다.\n" }
            sseEmitter.complete()
        }
        sseEmitter.onError {
            logger.debug { "\n\n${memberId}번 사용자의 연결 요청이 실패하여 종료합니다.\n" }
            sseEmitter.complete()
        }
    }

    fun sendMessage(
        message: NotificationSendable,
        memberId: Int
    ): SseEmitter {
        val sseEmitter = sseEmitterRepository.findByMemberId(memberId)
        try {
            sseEmitter.send(
                SseEmitter.event()
                    .id(memberId.toString())
                    .name(message.getMessageType().name)
                    .data(message.getData())
            )
        } catch (e: IOException) {
            sseEmitterRepository.deleteById(memberId)
        }
        return sseEmitter
    }
}