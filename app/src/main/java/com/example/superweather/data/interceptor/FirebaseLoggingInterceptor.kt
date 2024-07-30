package com.example.superweather.data.interceptor

import com.example.superweather.data.firebase.FirebaseEvents
import com.example.superweather.data.firebase.FirebaseParams
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.internal.commonIsSuccessful

class FirebaseLoggingInterceptor(private val firebaseAnalytics: FirebaseAnalytics) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (response.commonIsSuccessful) {
            sendSuccessRequestEvent(request)
        } else {
            sendFailureResponseEvent(response)
        }
        return response
    }

    private fun sendSuccessRequestEvent(request: Request) {
        firebaseAnalytics.logEvent(FirebaseEvents.HTTP_REQUEST) {
            param(FirebaseParams.URL, request.url.toString())
            param(FirebaseParams.HEADERS, request.headers.toString())
            param(FirebaseParams.METHOD, request.method)
            param(FirebaseParams.BODY, request.body.toString())
        }
    }

    private fun sendFailureResponseEvent(response: Response) {
        firebaseAnalytics.logEvent(FirebaseEvents.HTTP_EXCEPTION) {
            param(FirebaseParams.URL, response.request.url.toString())
            param(FirebaseParams.CODE, response.code.toString())
            param(FirebaseParams.HEADERS, response.request.headers.toString())
            param(FirebaseParams.METHOD, response.request.method)
            param(FirebaseParams.BODY, response.request.body.toString())
            param(FirebaseParams.CODE, response.code.toString())
        }
    }
}
