package com.example.nasaapi

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

private const val Base_url="https://api.nasa.gov/planetary/"
private val retrofit=Retrofit.Builder()
    .client(getUnsafeOkHttpClient()?.build())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(Base_url)
    .build()

interface ApiService {
    @GET("apod")
    fun getUserdata(@Query("api_key") api_key:String,@Query("count") count:String):
            Call<List<PictureInfo>>

}
fun getUnsafeOkHttpClient(): OkHttpClient.Builder? {
    return try {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(
            object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<X509Certificate?>?, authType: String?) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<X509Certificate?>?, authType: String?) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }

            }
        )

        // Install the all-trusting trust manager
        val sslContext: SSLContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory: SSLSocketFactory = sslContext.getSocketFactory()
        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        builder.hostnameVerifier { hostname, session -> true }
        builder
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}
object api{
    val retrofitService:ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}