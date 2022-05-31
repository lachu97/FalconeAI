package com.example.falconeai.domain.RepoImpl

import com.example.falconeai.commons.APIendpoints
import com.example.falconeai.data.models.auth
import com.example.falconeai.data.repository.TokenRepository
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class TokenRepoImpl(val client: HttpClient) : TokenRepository {
    override suspend fun getToken(): auth {
        return client.post(APIendpoints.GET_TOKEN_API) {
            headers {
               accept(ContentType.Application.Json)
            }
            body = emptyList<Any>()
        }
    }
}