package com.example.a93m

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://10.0.2.2:8080/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

data class CoursApi(
    val id: Int,
    val nom: String,
    val description: String,
    val prix: Double
)

interface CoursApiService {
    @GET("api/cours")
    suspend fun getAllCours(): List<CoursApi>
}

object CoursRetrofit {
    val retrofitService: CoursApiService by lazy {
        retrofit.create(CoursApiService::class.java)
    }
}

