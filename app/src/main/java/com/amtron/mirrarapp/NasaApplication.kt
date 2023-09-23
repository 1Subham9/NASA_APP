package com.amtron.mirrarapp

import android.app.Application
import com.amtron.mirrarapp.api.NasaService
import com.amtron.mirrarapp.api.RetrofitHelper
import com.amtron.mirrarapp.repository.APODRepository

class NasaApplication: Application() {

    lateinit var apodRepository: APODRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(NasaService::class.java)
        apodRepository = APODRepository(quoteService)
    }


}