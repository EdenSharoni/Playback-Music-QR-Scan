package com.testm.interviewproject.utils

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.testm.interviewproject.R
import es.dmoral.toasty.Toasty

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        Toasty.Config.getInstance().setTextSize(20).apply()
    }

    companion object {
        lateinit var context: Context
        var BASE_URl = "http://localhost/"

        fun errorToast(msg: String) = Toasty.custom(context, msg, R.drawable.error_message_sign, R.color.colorAccent, Toast.LENGTH_SHORT,true,true).show()

        fun infoToast(msg: String) = Toasty.custom(context, msg, R.drawable.error_message_sign, R.color.colorAccent, Toast.LENGTH_SHORT, true, true).show()

    }
}