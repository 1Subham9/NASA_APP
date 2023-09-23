package com.amtron.mirrarapp.util

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.Window
import cn.pedant.SweetAlert.SweetAlertDialog
import com.amtron.mirrarapp.R

class NotificationsHelper {


    fun getSuccessAlert(context: Context, text: String) {
        SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText("SUCCESS!")
            .setContentText(text)
            .show()
    }

    fun getWarningAlert(context: Context, text: String) {
        SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
            .setTitleText("WARNING!")
            .setContentText(text)
            .show()
    }

    fun getErrorAlert(context: Context, text: String) {
        SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("ERROR!")
            .setContentText(text)
            .show()
    }


    private var loadingDialog: Dialog?=null

    private lateinit var noInternetDialog : Dialog



    fun showLoadingDialog(context: Context) {

        if(loadingDialog==null){
            loadingDialog = Dialog(context)
            loadingDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            loadingDialog!!.setCancelable(false)
            loadingDialog!!.setContentView(R.layout.loading_view)
            Log.d("SUBHAM", "showLoadingDialog: ")
        }



        loadingDialog!!.show()

    }

    fun cancelLoadingDialog() {
        loadingDialog?.dismiss()
        loadingDialog=null
    }


    fun showNoInternetDialog(activity: Activity) {
        noInternetDialog = Dialog(activity)
        noInternetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        noInternetDialog.setCancelable(true)
        noInternetDialog.setContentView(R.layout.no_internet_dialog)
        noInternetDialog.show()

    }


    fun cancelNoInternetDialog() {
        if(noInternetDialog!=null)
            noInternetDialog.dismiss()
    }


}