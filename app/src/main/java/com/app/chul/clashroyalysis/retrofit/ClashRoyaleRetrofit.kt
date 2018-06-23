package com.app.chul.clashroyalysis.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClashRoyaleRetrofit {

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.royaleapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val service: ClashRoyaleService = retrofit.create(ClashRoyaleService::class.java)

    fun getService(): ClashRoyaleService = service

}