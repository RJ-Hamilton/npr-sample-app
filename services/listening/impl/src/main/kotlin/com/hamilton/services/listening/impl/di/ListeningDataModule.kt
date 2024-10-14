package com.hamilton.services.listening.impl.di

import com.hamilton.services.listening.api.ListeningApi
import com.hamilton.services.listening.api.ListeningRepository
import com.hamilton.services.listening.impl.ListeningRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://legacy.npr.org/assets/api/"

@Module
@InstallIn(SingletonComponent::class)
object ListeningDataModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val networkJson = Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                networkJson.asConverterFactory(
                    "application/json".toMediaType()
                )
            )
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideListeningApi(retrofit: Retrofit): ListeningApi {
        return retrofit.create(ListeningApi::class.java)
    }

    @Provides
    @Singleton
    fun provideListeningRepository(listeningApi: ListeningApi): ListeningRepository {
        return ListeningRepositoryImpl(listeningApi)
    }
}