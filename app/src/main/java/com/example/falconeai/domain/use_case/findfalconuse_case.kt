package com.example.falconeai.domain.use_case

import android.accounts.NetworkErrorException
import com.example.falconeai.commons.Resource
import com.example.falconeai.data.models.request
import com.example.falconeai.data.models.response
import com.example.falconeai.domain.RepoImpl.FindfalconImpl
import io.ktor.client.call.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.HttpRetryException
import javax.inject.Inject

class findFalconUseCase @Inject constructor(
    val findfalconImpl: FindfalconImpl
) {
    operator fun invoke(data: request): Flow<Resource<response>> = flow {
        try {
            emit(Resource.Loading<response>())
            delay(100L)
            val response = findfalconImpl.findFalcon(data)
            emit(Resource.Success<response>(data = response))
        } catch (e: Exception) {
            emit(Resource.Error<response>(e.localizedMessage))
        } catch (e: NoTransformationFoundException) {
            emit(Resource.Error<response>(e.localizedMessage))
        } catch (e: HttpRetryException) {
            emit(Resource.Error<response>(e.localizedMessage))
        } catch (e: NetworkErrorException) {
            emit(Resource.Error<response>(e.message))
        }
    }
}