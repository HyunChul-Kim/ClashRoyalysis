package com.app.chul.clashroyalysis.retrofit

import com.app.chul.clashroyalysis.jsonobject.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ClashRoyaleService {

    /*@GET("/player/{tag}")
    fun getPlayer(@Path("tag") tag: String): Observable<PlayerData>*/

    @GET("/player/{tag}")
    fun getPlayer(@Path("tag") tag: String): Call<PlayerData>

    @GET("/player/{tag}")
    fun getPlayers(@Path("tag") tag: String): Call<PlayerDataList>

    @GET("/player/{tag}/chests")
    fun getUpcomingBox(@Path("tag") tag: String): Call<UpcomingBoxData>

    @GET("/player/{tag}/battles")
    fun getPlayerBattles(@Path("tag") tag: String): Call<BattleData>

    @GET("/popular/decks")
    fun getPopularDecks(): Call<PopularDeckList>

    /**
     * @param page is not working (API issue)
     */
    @GET("/popular/decks?max=10")
    fun getPopularDecks(@Query("page") page: Int): Call<PopularDeckList>

    @GET("/top/players/{LOCATION_KEY}")
    fun getTopPlayers(@Path("LOCATION_KEY") location: String): Call<TopPlayerList>

    @GET("/top/players/{LOCATION_KEY}")
    fun getTopPlayers(@Path("LOCATION_KEY") location: String, @Query("max") max: Int): Call<TopPlayerList>

    @GET("/top/players/{LOCATION_KEY}")
    fun getTopPlayers(@Path("LOCATION_KEY") location: String, @Query("max") max: Int, @Query("page") page: Int): Observable<TopPlayerList>

}