package io.linkey.app.android.data.di

import android.content.Context
import android.util.Log
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.linkey.app.android.data.remote.service.SampleService
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    private const val BASE_URL = "https://api.sampleapis.com/"

    @Provides
    @Singleton
    fun provideSampleService() = provideRetrofit().create(SampleService::class.java)

    private fun provideRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideOkHttpClient(provideLoggingInterceptor()))
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create()))
        .build()

    private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val b = OkHttpClient.Builder()
            .connectTimeout(30000, TimeUnit.MILLISECONDS)
            .writeTimeout(30000, TimeUnit.MILLISECONDS)
            .readTimeout(30000, TimeUnit.MILLISECONDS)

        b.addInterceptor(interceptor)
        b.addInterceptor { chain ->
            val request: Request =
                chain.request()
                    .newBuilder()
//                    .addHeader(API_HEADER, "Bearer ${sp.getAccessToken()}")
                    .build()
            chain.proceed(request)
        }

        return b.build()
    }

    // 네트워크 요청/응답을 로그에 표시하는 Interceptor 객체를 생성합니다.
    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

}