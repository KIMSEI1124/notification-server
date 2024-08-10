package com.wypl.notification.emitter.repository

import org.springframework.stereotype.Repository
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.ConcurrentHashMap

@Repository
class SseEmitterRepository(
    private val emitters: ConcurrentHashMap<Int, SseEmitter> = ConcurrentHashMap(),
) {
    fun save(memberId: Int, sse: SseEmitter): SseEmitter {
        emitters[memberId] = sse;
        return sse
    }

    fun findByMemberId(memberId: Int): SseEmitter {
        val sseEmitter = emitters[memberId]
        if (sseEmitter != null) {
            return sseEmitter
        }
        throw NoSuchElementException()  // 로그인을 안 한 경우
    }

    fun deleteById(memberId: Int) {
        emitters.remove(memberId)
    }
}