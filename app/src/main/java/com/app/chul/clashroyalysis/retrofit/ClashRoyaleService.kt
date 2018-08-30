package com.app.chul.clashroyalysis.retrofit

import com.app.chul.clashroyalysis.jsonobject.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ClashRoyaleService {

    @GET("/player/{tag}")
    fun getPlayer(@Path("tag") tag: String): Call<PlayerData>

    @GET("/player/{tag}")
    fun getPlayers(@Path("tag") tag: String): Call<PlayerDataList>

    @GET("/popular/decks?max=100")
    fun getPopularDecks(): Call<PopularDeckList>

    /**
     * @param page is not working (API issue)
     */
    @GET("/popular/decks?max=10")
    fun getPopularDecks(@Query("page") page: Int): Call<PopularDeckList>

    @GET("/top/players/{LOCATION_KEY}")
    fun getTopPlayers(@Path("LOCATION_KEY") location: String): Call<TopPlayerList>

    @GET("/player/{tag}/chests")
    fun getUpcomingBox(@Path("tag") tag: String): Call<UpcomingBoxData>
}