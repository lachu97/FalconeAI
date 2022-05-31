package com.example.falconeai.Di

import com.example.falconeai.domain.RepoImpl.PlanetRepoImpl
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
}