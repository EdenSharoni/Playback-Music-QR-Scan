package com.testm.interviewproject.repository

import androidx.lifecycle.LiveData
import com.testm.demosdk.Audio
import com.testm.interviewproject.api.RetrofitBuilder
import com.testm.interviewproject.utils.App.Companion.errorToast
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {

    var job: CompletableJob? = null

    fun getAudios(url: String): LiveData<ArrayList<Audio>> {
        job = Job()
        return object : LiveData<ArrayList<Audio>>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        try {
                            val audioArrayList = RetrofitBuilder.apiService.getAudios(url)
                            withContext(Main) {
                                value = audioArrayList
                                theJob.complete()
                            }
                        } catch (err: Exception) {
                            withContext(Main) {
                                errorToast("This QR Scan is not a playback list")
                            }
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs() {
        job?.cancel()
    }
}