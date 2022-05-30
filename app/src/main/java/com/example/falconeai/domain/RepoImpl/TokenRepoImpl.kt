package com.example.falconeai.domain.RepoImpl

import com.example.falconeai.commons.APIendpoints
import com.example.falconeai.data.models.auth
import com.example.falconeai.data.repos.TokenRepository
import io.ktor.client.*
import io.ktor.client.request.*

class TokenRepoImpl(val client: HttpClient) : TokenRepository {
    override suspend fun getToken(): auth {
        return client.get(APIendpoints.GET_TOKEN_API)
    }
}