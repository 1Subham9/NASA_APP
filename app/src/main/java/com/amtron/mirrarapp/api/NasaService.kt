package com.amtron.mirrarapp.api

import com.amtron.mirrarapp.model.APODResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaService {

    @GET("planetary/apod")
    suspend fun getAPOD(@Query("api_key") apiKey: String): Response<APODResponse>?
}