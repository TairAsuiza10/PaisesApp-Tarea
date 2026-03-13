package com.example.paisesapp_tarea

import com.google.gson.annotations.SerializedName

// Aquí mapeamos los 10 atributos requeridos
data class Pais(
    @SerializedName("name") val nombre: Nombre,
    @SerializedName("capital") val capital: List<String>?,
    @SerializedName("region") val region: String,
    @SerializedName("subregion") val subregion: String?,
    @SerializedName("population") val poblacion: Long,
    @SerializedName("area") val area: Double,
    @SerializedName("continents") val continentes: List<String>,
    @SerializedName("flags") val banderas: Banderas,
    @SerializedName("timezones") val zonasHorarias: List<String>,
    @SerializedName("startOfWeek") val inicioSemana: String
)

data class Nombre(
    @SerializedName("official") val oficial: String,
    @SerializedName("common") val comun: String
)

data class Banderas(
    @SerializedName("png") val imagenPng: String
)