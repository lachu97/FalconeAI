package com.example.falconeai.Di

import com.example.falconeai.domain.RepoImpl.FindfalconImpl
import com.example.falconeai.domain.RepoImpl.PlanetRepoImpl
import com.example.falconeai.domain.RepoImpl.TokenRepoImpl
import com.example.falconeai.domain.RepoImpl.VehicleRepoImpl
import com.example.falconeai.domain.use_case.getTokenUseCase
import com.example.falconeai.domain.use_case.planetUseCase
import com.example.falconeai.domain.use_case.vehicleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Providers {
    @Provides
    @Singleton
    fun provideHttpClient() : HttpClient {
        return HttpClient(CIO){
            install(JsonFeature){
                serializer = GsonSerializer()
            }
            install(Logging) {
                level = LogLevel.BODY
            }
            install(HttpTimeout){
                requestTimeoutMillis = 5000L
            }
        }
    }
    @Provides
    @Singleton
    fun providePlanetRepo(client: HttpClient) : PlanetRepoImpl {
        return PlanetRepoImpl(client)
    }
    @Provides
    @Singleton
    fun provideVehicleRepo(client: HttpClient) : VehicleRepoImpl {
        return VehicleRepoImpl(client = client)
    }
    @Provides
    @Singleton
    fun provideTokenRepo(client: HttpClient) : TokenRepoImpl {
        return TokenRepoImpl(client = client)
    }
    @Provides
    @Singleton
    fun provideFindFalcon(client: HttpClient) : FindfalconImpl {
        return FindfalconImpl(client = client)
    }
    @Provides
    @Singleton
    fun provideVehicleUseCase(vehicleRepoImpl: VehicleRepoImpl) : vehicleUseCase {
        return vehicleUseCase(vehicleRepoImpl)
    }
    @Provides
    @Singleton
    fun providePlanetUseCase(planetRepoImpl: PlanetRepoImpl) : planetUseCase {
        return planetUseCase(planetRepoImpl)
    }
    @Provides
    @Singleton
    fun provideTokenUseCase(tokenRepoImpl: TokenRepoImpl) : getTokenUseCase {
        return getTokenUseCase(tokenRepoImpl)
    }
}