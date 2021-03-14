package com.testm.interviewproject.api

import com.testm.interviewproject.utils.App.Companion.BASE_URl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val retrofitBuilder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy { retrofitBuilder.create(ApiService::class.java) }
}