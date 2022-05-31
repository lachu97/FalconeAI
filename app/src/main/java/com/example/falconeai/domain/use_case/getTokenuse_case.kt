package com.example.falconeai.domain.use_case

import android.accounts.NetworkErrorException
import com.example.falconeai.commons.Resource
import com.example.falconeai.data.models.auth
import com.example.falconeai.domain.RepoImpl.TokenRepoImpl
import io.ktor.client.call.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.HttpRetryException
import javax.inject.Inject

class getTokenUseCase @Inject constructor(
    val tokenrepo: TokenRepoImpl
) {
    operator fun invoke(): Flow<Resource<auth>> = flow {
        try {
            emit(Resource.Loading<auth>(auth(token = "Loading")))
            delay(100L)
            val token = tokenrepo.getToken()
            emit(Resource.Success(data = token))

        } catch (e: Exception) {
            emit(Resource.Error<auth>(e.localizedMessage))
        } catch (e: NoTransformationFoundException) {
            emit(Resource.Error<auth>(e.localizedMessage))
        } catch (e: HttpRetryException) {
            emit(Resource.Error<auth>(e.localizedMessage))
        } catch (e: NetworkErrorException) {
            emit(Resource.Error<auth>(e.message))
        }
    }
}