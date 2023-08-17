package com.elanyudho.core.data.remote.service

import com.elanyudho.core.data.remote.response.DetailGameResponse
import com.elanyudho.core.data.remote.response.GamesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun getGames(
        @Query("key") key: String,
        @Query("page") page: String,
        @Query("page_size")pageSize: Int
    ): Response<GamesResponse>

    @GET("games/{id}")
    suspend fun getDetailGame(
        @Path("id")id: String,
        @Query("key") key: String,
    ): Response<DetailGameResponse>

    @GET("games")
    suspend fun getSearchGames(
        @Query("key") key: String,
        @Query("page") page: String,
        @Query("page_size")pageSize: Int,
        @Query("search")search: String
    ): Response<GamesResponse>
}