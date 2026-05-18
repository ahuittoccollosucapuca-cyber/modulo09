package com.example.modulo09.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// 1. Una interfaz para definir la ruta de Google
interface AmphibianApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<AmphibianModel>
}

// 2. Un objeto único (Singleton) para configurar Retrofit con OkHttp
object RetrofitClient {
    private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val apiService: AmphibianApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AmphibianApiService::class.java)
    }
}