package de.fabianrump.network.interceptor

import de.fabianrump.network.BuildConfig.PRIVATE_API_KEY
import de.fabianrump.network.BuildConfig.PUBLIC_API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest

class MarvelAuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().let { request ->
            val timestamp = System.currentTimeMillis()

            val privateKey = PRIVATE_API_KEY
            val publicKey = PUBLIC_API_KEY

            val hash = "$timestamp$privateKey$publicKey".md5()

            val newUrl = request
                .url()
                .newBuilder()
                .addQueryParameter("ts", timestamp.toString())
                .addQueryParameter("apikey", publicKey)
                .addQueryParameter("hash", hash)
                .build()

            request.newBuilder().url(newUrl).build()
        }
        return chain.proceed(newRequest)
    }
}

private fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}