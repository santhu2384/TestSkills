package com.example.core_di

import com.example.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton
import retrofit2.Retrofit



@Module
@InstallIn(SingletonComponent::class)
object NetworkModule
{
    val Base_Url = "https://jsonplaceholder.typicode.com/";

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit
    {
        return Retrofit.Builder().baseUrl(Base_Url).build()
    }

    @Provides
    fun provideApservice(retrofit: Retrofit): ApiService
    {
        return retrofit.create(ApiService::class.java)
    }

}