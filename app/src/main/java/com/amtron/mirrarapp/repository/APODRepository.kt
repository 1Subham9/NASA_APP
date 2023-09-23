package com.amtron.mirrarapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amtron.mirrarapp.api.NasaService
import com.amtron.mirrarapp.model.APODResponse
import retrofit2.Response

class APODRepository(
    private val nasaService: NasaService
) {

    private val apodLiveData = MutableLiveData<Response<APODResponse>?>()

    val apodData: MutableLiveData<Response<APODResponse>?>
        get() = apodLiveData


    suspend fun getApodData(apiKey: String) {


        val result = nasaService.getAPOD(apiKey)
        apodLiveData.postValue(result)

    }
}