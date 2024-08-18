package com.wypl.notification.emitter.controller

import com.wypl.notification.emitter.service.EmitterSubscribeService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@RequestMapping("/notification")
@RestController
class NotificationController(
    private val emitterSubscribeService: EmitterSubscribeService
) {
    @PostMapping("/v1/subscribe/{memberId}", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun subscribe(
        @PathVariable memberId: Int,
        @RequestBody requestBody: SubscribeRequest  // TODO: 인터셉터로 JWT 서버에 보내기?, 추후 검증은?
    ): ResponseEntity<SseEmitter> {
        val sseEmitter: SseEmitter = emitterSubscribeService.connections(memberId)
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(sseEmitter)
    }
}