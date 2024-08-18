package com.wypl.notification.emitter.service

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

interface SubscribeService {
    /**
     * 회원과 서버를 Emitter 기능을 사용하여 연결합니다.
     *
     * @param memberId  연결 요청한 회원의 식별자
     */
    fun connections(memberId: Int): SseEmitter
}