package com.app.chul.clashroyalysis.retrofit

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ClashRoyaleService {

    @GET("/player/{tag}")
    fun getPlayer(@Path("tag") tag: String): Call<ArrayList<JsonObject>>
}