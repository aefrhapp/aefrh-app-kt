package aefrh.es.aefrh.remote

import aefrh.es.aefrh.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-Parse-Application-Id", BuildConfig.PARSE_API_ID)
            .addHeader("X-Parse-REST-API-Key", BuildConfig.PARSE_REST_API_ID)
            .build()
        return chain.proceed(request)
    }

}