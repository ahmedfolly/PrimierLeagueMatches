package com.example.primierleaguematches.data

import com.example.primierleaguematches.domain.models.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
//    @GET("competitions/PL/matches?status=SCHEDULED")
//    suspend fun getScheduledMatches(): Response

    @GET("competitions/PL/matches")
    suspend fun getTodayMatches(
        @Query("dateFrom") dateFrom: String,
        @Query("dateTo") dateTo: String
    ):retrofit2.Response<Response>
}