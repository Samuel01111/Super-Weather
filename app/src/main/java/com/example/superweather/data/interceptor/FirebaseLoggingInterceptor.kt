package com.example.superweather.data.interceptor

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import okhttp3.Interceptor
import okhttp3.Response

class FirebaseLoggingInterceptor(private val firebaseAnalytics: FirebaseAnalytics) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // Log request details to Firebase
        val bundle = Bundle().apply {
            putString("url", request.url.toString())
            putString("headers", request.headers.toString())
            putString("method", request.method)
            putString("body", request.body.toString())
        }
        firebaseAnalytics.logEvent("http_request", bundle)

        return chain.proceed(request)
    }
}
