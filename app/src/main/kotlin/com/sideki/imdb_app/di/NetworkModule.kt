package com.sideki.imdb_app.di

import com.sideki.imdb_app.data.api.ImdbApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TIMEOUT = 10L

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val HTTP_SCHEME = "https://"
    private val API_PATH = "imdb-api.com/ru/API/"

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder().apply {
        followRedirects(false)
        followSslRedirects(false)
        connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        readTimeout(TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        val logging = HttpLoggingInterceptor().apply {
            setLevel(Level.BODY);
        }
        addInterceptor(logging)
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl("$HTTP_SCHEME$API_PATH")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideApi(retrofit: Retrofit) = retrofit.create(ImdbApi::class.java)
}