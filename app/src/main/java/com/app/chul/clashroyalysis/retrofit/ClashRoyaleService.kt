package com.app.chul.clashroyalysis.retrofit

import com.app.chul.clashroyalysis.jsonobject.UserData
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ClashRoyaleService {
    @GET("/player/{tag}")
    fun getPlayer(@Path("tag") tag: String): Call<UserData>
}