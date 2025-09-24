package com.it.userdirectory.di

import com.it.userdirectory.data.datasource.PostDataSource
import com.it.userdirectory.data.datasource.UsersDataSource
import com.it.userdirectory.data.repository.PostRepositoryImpl
import com.it.userdirectory.data.repository.UserRepositoryImpl
import com.it.userdirectory.domain.network.NetworkUrlProvider
import com.it.userdirectory.domain.repository.PostRepository
import com.it.userdirectory.domain.repository.UserRepository
import com.it.userdirectory.domain.usecase.PostUseCase
import com.it.userdirectory.domain.usecase.UserUseCase
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient() : OkHttpClient {
        return OkHttpClient
            .Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }




    @Provides
    @Singleton
    fun provideRetrofit(
        httpClient: OkHttpClient
    ) : Retrofit {
        val networkJson = Json { ignoreUnknownKeys = true }
        val converter= networkJson.asConverterFactory("application/json".toMediaType())
        return Retrofit.Builder()
            .baseUrl(NetworkUrlProvider.BASE_URL)
            .client(httpClient)
            .addConverterFactory(converter)
            .build()
    }


    @Provides
    @Singleton
    fun provideListApiService(retrofit: Retrofit) : UsersDataSource {
        return retrofit.create(UsersDataSource::class.java)
    }

    @Provides
    @Singleton
    fun providerUserRepository(
        datasource: UsersDataSource
    ) : UserRepository {
        return UserRepositoryImpl(datasource)
    }

    @Provides
    @Singleton
    fun providePostApiService(retrofit: Retrofit) : PostDataSource {
        return retrofit.create(PostDataSource::class.java)
    }

    @Provides
    @Singleton
    fun providerPostRepository(
        datasource: PostDataSource
    ) : PostRepository {
        return PostRepositoryImpl(datasource)
    }

    @Provides
    @Singleton
    fun provideUserUseCase(repository: UserRepository): UserUseCase {
        return UserUseCase(repository)
    }

    @Provides
    @Singleton
    fun providePostUseCase(repository: PostRepository): PostUseCase {
        return PostUseCase(repository)
    }



}