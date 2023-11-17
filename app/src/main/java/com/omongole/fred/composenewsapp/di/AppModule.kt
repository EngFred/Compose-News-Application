package com.omongole.fred.composenewsapp.di

import com.omongole.fred.composenewsapp.data.api.ApiInterface
import com.omongole.fred.composenewsapp.data.repository.NewsRepository
import com.omongole.fred.composenewsapp.data.source.RemoteSource
import com.omongole.fred.composenewsapp.domain.NewsRepositoryImpl
import com.omongole.fred.composenewsapp.domain.UseCases
import com.omongole.fred.composenewsapp.utils.Constants.API_BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofitInstance() : Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        val httpClient = OkHttpClient().newBuilder().addInterceptor(httpLoggingInterceptor)
        httpClient.readTimeout(60, TimeUnit.SECONDS)
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun providesApiInstance( retrofit: Retrofit ) : ApiInterface = retrofit.create(ApiInterface::class.java)

    @Provides
    @Singleton
    fun providesNewsRepository( api: ApiInterface) : NewsRepositoryImpl = NewsRepositoryImpl( api )

    @Provides
    @Singleton
    fun providesUseCases( repository: NewsRepositoryImpl ) : UseCases = UseCases( repository )

}