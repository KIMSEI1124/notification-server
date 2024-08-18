package com.wypl.notification.infrastructure

import com.wypl.notification.infrastructure.exception.AuthClientErrorCode
import com.wypl.notification.infrastructure.exception.AuthClientException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

@Component
class AuthClient(
    private val restTemplate: RestTemplate = RestTemplate()
) {
    /**
     * What's Your Plan!에 JWT를 전송하여 올바른 사용자인지 확인한 후 결과를 반환합니다.
     *
     * @param jwt JsonWebToken
     *
     * @return memberId
     */
    fun authenticate(jwt: String): Int {
        val response = restTemplate.getForEntity<AuthMemberResponse>(
            "", AuthMemberRequest(
                jsonWebToken = jwt
            )
        )
        if (response.statusCode == HttpStatus.UNAUTHORIZED) {
            throw AuthClientException(AuthClientErrorCode.UNAUTHORIZED)
        }
        return response.body!!.memberId;
    }
}

private data class AuthMemberRequest(val jsonWebToken: String)
private data class AuthMemberResponse(val memberId: Int)