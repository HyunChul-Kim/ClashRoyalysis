package com.app.chul.clashroyalysis.retrofit

import com.app.chul.clashroyalysis.jsonobject.PopularDeckList
import com.app.chul.clashroyalysis.jsonobject.PlayerData
import com.app.chul.clashroyalysis.jsonobject.TopPlayerList
import com.app.chul.clashroyalysis.jsonobject.UpcomingBoxData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ClashRoyaleService {

    @GET("/player/{tag}")
    fun getPlayer(@Path("tag") tag: String): Call<PlayerData>

    @GET("/player/{tag}")
    fun getPlayers(@Path("tag") tag: String): Call<List<PlayerData>>

    @GET("/popular/decks?max=10")
    fun getPopularDecks(): Call<PopularDeckList>

    @GET("/top/players/{LOCATION_KEY}")
    fun getTopPlayers(@Path("LOCATION_KEY") location: String): Call<TopPlayerList>

    @GET("/player/{tag}/chests")
    fun getUpcomingBox(@Path("tag") tag: String): Call<UpcomingBoxData>
}