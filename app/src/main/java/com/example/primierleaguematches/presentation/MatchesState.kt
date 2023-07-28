package com.example.primierleaguematches.presentation

import com.example.primierleaguematches.domain.models.ErrorResponse
import com.example.primierleaguematches.domain.models.Response
import okhttp3.ResponseBody

sealed class MatchesState {
    object Loading : MatchesState()
    data class Success(val data: Response) : MatchesState()
    data class Error(val e: String) : MatchesState()
}
