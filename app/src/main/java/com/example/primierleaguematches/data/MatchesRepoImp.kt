package com.example.primierleaguematches.data

import com.example.primierleaguematches.ResultWorker
import com.example.primierleaguematches.domain.models.Response
import com.example.primierleaguematches.domain.repos.MatchesRepo

class MatchesRepoImp(private val apiService: APIService) : MatchesRepo {
//    override suspend fun getScheduledMatches(): ResultWorker<Response> {
//        ResultWorker.Loading
//        return try {
//            ResultWorker.Success(apiService.getScheduledMatches())
//        } catch (e: Exception) {
//            ResultWorker.Error(e)
//        }
//    }

    override suspend fun getTodayMatches(dateFrom: String, dateTo: String): ResultWorker<Response> {
        ResultWorker.Loading
        return try {
            ResultWorker.Success(apiService.getTodayMatches(dateFrom, dateTo).body()!!)
        } catch (e: Exception) {
            ResultWorker.Error(apiService.getTodayMatches(dateFrom, dateTo).errorBody()!!.string())
        }
    }

}

