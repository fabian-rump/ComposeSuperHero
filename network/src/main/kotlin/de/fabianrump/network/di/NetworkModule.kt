package de.fabianrump.network.di

import de.fabianrump.network.interceptor.MarvelAuthenticationInterceptor
import de.fabianrump.network.service.MarvelService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { MarvelAuthenticationInterceptor() }
    single { GsonConverterFactory.create() }
    single { provideRetrofit(get(), get()) }
    single { provideMarvelService(get()) }
    single { provideRetrofitHttpClient(get()) }
    single { provideRetrofitHttpClientBuilder(get()) }
}

private fun provideMarvelService(retrofit: Retrofit): MarvelService = retrofit.create(MarvelService::class.java)

private fun provideRetrofit(httpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit =
    Retrofit.Builder()
        .client(httpClient)
        .baseUrl("https://gateway.marvel.com/")
        .addConverterFactory(gsonConverterFactory)
        .build()

private fun provideRetrofitHttpClient(retrofitOkHttpClientBuilder: OkHttpClient.Builder): OkHttpClient =    retrofitOkHttpClientBuilder.build()

private fun provideRetrofitHttpClientBuilder(authenticationInterceptor: MarvelAuthenticationInterceptor) =
    OkHttpClient.Builder()
        .addNetworkInterceptor(authenticationInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)