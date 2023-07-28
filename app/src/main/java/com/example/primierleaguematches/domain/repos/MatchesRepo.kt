package com.example.primierleaguematches.domain.repos

import com.example.primierleaguematches.ResultWorker
import com.example.primierleaguematches.domain.models.Response

interface MatchesRepo {
//    suspend fun getScheduledMatches(): ResultWorker<Response>
    suspend fun getTodayMatches(dateFrom:String,dateTo:String): ResultWorker<Response>
}