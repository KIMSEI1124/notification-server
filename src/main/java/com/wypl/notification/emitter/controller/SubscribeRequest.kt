package com.wypl.notification.emitter.controller

import com.fasterxml.jackson.annotation.JsonProperty

data class SubscribeRequest(
    @JsonProperty("access_token") val accessToken: String,
)
