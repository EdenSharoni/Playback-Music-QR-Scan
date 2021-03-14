package com.testm.interviewproject.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.zxing.integration.android.IntentIntegrator
import com.testm.demosdk.DemoSdk
import com.testm.interviewproject.PlayBack.PlaybackActivity
import com.testm.interviewproject.R
import com.testm.interviewproject.utils.App.Companion.infoToast

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.arrayListOfAudios.observe(this, Observer {
            DemoSdk.arrayListOfAudios = it
            startActivity(Intent(applicationContext, PlaybackActivity::class.java))
        })

        findViewById<Button>(R.id.startBtn).setOnClickListener { viewModel.scanQRCode(this) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) infoToast("Cancelled")
            else viewModel.fetchData(result.contents)
        } else super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJobs()
    }
}