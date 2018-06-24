package com.app.chul.clashroyalysis.retrofit

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClashRoyaleRetrofit {

    private val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.royaleapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    private val service: ClashRoyaleService = retrofit.create(ClashRoyaleService::class.java)

    fun getService(): ClashRoyaleService = service

}