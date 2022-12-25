package com.ssv.binchecker.di.modules

import com.ssv.binchecker.data.remoteDataSource.apiCall.BinApiCall
import com.ssv.binchecker.di.ApplicationScope
import com.ssv.binchecker.di.unsafeOkHttpClient.UnsafeOkHttpClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
interface NetworkModule {

    companion object {

        @ApplicationScope
        @Provides
        fun provideApiCalls(retrofit: Retrofit): BinApiCall {
            return retrofit.create(BinApiCall::class.java)
        }

        @ApplicationScope
        @Provides
        fun provideRetrofit(
            baseUrl: String,
            okHttpClient: OkHttpClient,
            gsonConverterFactory: GsonConverterFactory
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()
        }

        @ApplicationScope
        @Provides
        fun provideGsonConverterFactory(): GsonConverterFactory {
            return GsonConverterFactory.create()
        }

        @ApplicationScope
        @Provides
        fun provideBaseURL(): String {
            return "https://lookup.binlist.net/"
        }

        @ApplicationScope
        @Provides
        fun provideLoggingInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
        }

        @ApplicationScope
        @Provides
        fun provideOkHTTPClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
            return UnsafeOkHttpClient.unsafeOkHttpClient
        }
    }
}