package com.testm.interviewproject.api

import com.testm.demosdk.Audio
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getAudios(@Url url: String ): ArrayList<Audio>
}