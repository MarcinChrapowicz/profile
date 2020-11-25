package marcinchrapowicz.application.data.datasource

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor()).build()
}

fun httpLoggingInterceptor() = HttpLoggingInterceptor()
    .also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }
