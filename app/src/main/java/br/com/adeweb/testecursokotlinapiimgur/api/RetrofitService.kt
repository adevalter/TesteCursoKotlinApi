package br.com.adeweb.testecursokotlinapiimgur.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object RetrofitService {
    const val BASE_URL = "https://api.imgur.com/3/"

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20,TimeUnit.SECONDS)
        .addInterceptor( AuthInterceptor() )
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl( BASE_URL)
        .addConverterFactory( GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun<T>recuperarApi (classe: Class<T>): T {
        return  retrofit.create(classe)
    }
}