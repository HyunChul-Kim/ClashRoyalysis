package com.app.chul.clashroyalysis.retrofit

import com.app.chul.clashroyalysis.jsonobject.PopularDeckList
import com.app.chul.clashroyalysis.jsonobject.UserData
import com.app.chul.clashroyalysis.jsonobject.UserDataList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ClashRoyaleService {

    @GET("/player/{tag}")
    fun getPlayer(@Path("tag") tag: String): Call<UserData>

    @GET("/player/{tag}")
    fun getPlayers(@Path("tag") tag: String): Call<List<UserData>>

    @GET("/popular/decks?max=10")
    fun getPopularDecks(): Call<PopularDeckList>
}