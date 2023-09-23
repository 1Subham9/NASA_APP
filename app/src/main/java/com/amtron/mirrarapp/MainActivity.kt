package com.amtron.mirrarapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.amtron.mirrarapp.databinding.ActivityMainBinding
import com.amtron.mirrarapp.util.NotificationsHelper
import com.amtron.mirrarapp.viewModelFactory.APODViewModelFactory
import com.amtron.mirrarapp.viewmodel.APODViewModel
import com.amtron.retrofitroommvvm.util.NetworkUtils
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private lateinit var apodViewModel: APODViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var notificationsHelper: NotificationsHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Blocking The Dark Mode in Kotlin
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        notificationsHelper = NotificationsHelper()



//        This will check the Internet is Available or not
        if (NetworkUtils.isInternetAvailable(applicationContext)) {

            val repository = (application as NasaApplication).apodRepository

            apodViewModel =
                ViewModelProvider(this, APODViewModelFactory(repository))[APODViewModel::class.java]
            populate()
        } else {
            notificationsHelper.showNoInternetDialog(this)
        }



        binding.refresh.setOnClickListener {
            populate()
        }

    }

    private fun populate() {

        notificationsHelper.showLoadingDialog(this)


        // Getting the data from the view model
        apodViewModel.apoData.observe(this) {

            Log.d("DATA", it.toString())


            if (it != null) {
                if (it.isSuccessful) {

                    notificationsHelper.cancelLoadingDialog()

                    binding.title.text = "Title: ${it.body()?.title}"
                    binding.description.text = "Description: ${it.body()?.explanation}"

                    Glide.with(this).load(it.body()?.url).into(binding.image)

                } else {
                    notificationsHelper.cancelLoadingDialog()
                    notificationsHelper.getErrorAlert(this, "No Data")
                }


            } else {
                notificationsHelper.cancelLoadingDialog()
                notificationsHelper.getErrorAlert(this, "Server not Found")
            }
        }


    }
}