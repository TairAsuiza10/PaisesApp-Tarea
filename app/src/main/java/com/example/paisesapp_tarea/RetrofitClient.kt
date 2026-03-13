package com.example.paisesapp_tarea

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// 1. Definimos qué queremos pedirle a la API
interface ApiService {
    @GET("all")
    suspend fun obtenerPaises(): List<Pais>
}

// 2. Creamos el cliente que hace la conexión
object RetrofitClient {
    private const val BASE_URL = "https://restcountries.com/v3.1/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}