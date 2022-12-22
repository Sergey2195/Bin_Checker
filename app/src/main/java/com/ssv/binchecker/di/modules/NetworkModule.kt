package com.ssv.binchecker.di.modules

import android.os.Build
import com.ssv.binchecker.data.remoteDataSource.apiCall.BinApiCall
import com.ssv.binchecker.di.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
interface NetworkModule {

    companion object {

        @ApplicationScope
        @Provides
        fun provideApiCalls(retrofit: Retrofit): BinApiCall{
            return retrofit.create(BinApiCall::class.java)
        }

        @ApplicationScope
        @Provides
        fun provideRetrofit(
            baseUrl: String,
            okHttpClient: OkHttpClient,
            gsonConverterFactory: GsonConverterFactory
        ): Retrofit{
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
        fun provideBaseURL(): String{
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
            return getOkHttpBuilder()
                .addInterceptor(interceptor)
                .build()
        }

        private fun getOkHttpBuilder(): OkHttpClient.Builder =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                OkHttpClient().newBuilder()
            } else {
                getUnsafeOkHttpClient()
            }

        private fun getUnsafeOkHttpClient(): OkHttpClient.Builder =
            try {
                val trustAllCerts: Array<TrustManager> = arrayOf(
                    object : X509TrustManager {
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(
                            chain: Array<X509Certificate?>?,
                            authType: String?
                        ) = Unit

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(
                            chain: Array<X509Certificate?>?,
                            authType: String?
                        ) = Unit

                        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                    }
                )
                val sslContext: SSLContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())
                val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
                val builder = OkHttpClient.Builder()
                builder.sslSocketFactory(
                    sslSocketFactory,
                    trustAllCerts[0] as X509TrustManager
                )
                builder.hostnameVerifier { _, _ -> true }
                builder
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
    }
}