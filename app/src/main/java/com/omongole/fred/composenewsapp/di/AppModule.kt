package com.omongole.fred.composenewsapp.di

import android.content.Context
import androidx.room.Room
import com.omongole.fred.composenewsapp.data.local.ArticlesDao
import com.omongole.fred.composenewsapp.data.local.ArticlesDatabase
import com.omongole.fred.composenewsapp.data.local.ArticlesRepository
import com.omongole.fred.composenewsapp.data.local.ArticlesTypeConverter
import com.omongole.fred.composenewsapp.data.local.ArticlesUseCase
import com.omongole.fred.composenewsapp.data.remote.api.ApiInterface
import com.omongole.fred.composenewsapp.domain.local.ArticlesRepositoryImpl
import com.omongole.fred.composenewsapp.domain.remote.NewsRepositoryImpl
import com.omongole.fred.composenewsapp.domain.remote.UseCases
import com.omongole.fred.composenewsapp.utils.Constants.API_BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun providesUseCases( repository: NewsRepositoryImpl) : UseCases = UseCases( repository )

    @Provides
    @Singleton
    fun providesRoomDatabaseInstance( @ApplicationContext context: Context ) : ArticlesDatabase =
        Room.databaseBuilder( context, ArticlesDatabase::class.java, "Articles Database" )
            .addTypeConverter( ArticlesTypeConverter() )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesArticleDaoInstance( articlesDatabase: ArticlesDatabase ) : ArticlesDao =
        articlesDatabase.getDao

    @Provides
    @Singleton
    fun providesArticlesLocalRepository( articlesDao: ArticlesDao ) :  ArticlesRepository =
        ArticlesRepositoryImpl( articlesDao )

    @Provides
    @Singleton
    fun providesLocalUseCase( localRepository: ArticlesRepositoryImpl ) : ArticlesUseCase =
        ArticlesUseCase( localRepository )
}