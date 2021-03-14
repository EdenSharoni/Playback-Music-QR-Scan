package com.testm.interviewproject.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
import com.testm.demosdk.Audio
import com.testm.interviewproject.repository.Repository

class MainViewModel : ViewModel() {

    private val _url: MutableLiveData<String> = MutableLiveData()
    val arrayListOfAudios: LiveData<ArrayList<Audio>> = Transformations.switchMap(_url) { Repository.getAudios(it) }

    fun fetchData(url: String) {
        if (_url.value == url) return
        _url.value = url
    }

    fun scanQRCode(activity: MainActivity) {
        val integrator = IntentIntegrator(activity).apply {
            captureActivity = CaptureActivity::class.java
            setOrientationLocked(false)
            setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
            setPrompt("Scanning Code")
        }
        integrator.initiateScan()
    }

    fun cancelJobs() {
        Repository.cancelJobs()
    }
}