package com.example.primierleaguematches.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primierleaguematches.ResultWorker
import com.example.primierleaguematches.convertMutableToImmutable
import com.example.primierleaguematches.domain.models.ErrorResponse
import com.example.primierleaguematches.domain.models.Response
import com.example.primierleaguematches.domain.usecases.GetScheduledMatches
import com.example.primierleaguematches.domain.usecases.GetTodayMatchesUseCase
import com.example.primierleaguematches.getTodayDate
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    private val getTodayMatches: GetTodayMatchesUseCase
) : ViewModel() {
    init {
        fetchAllMatchesData(getTodayDate())
        fetchTodayMatchesData(getTodayDate(), getTodayDate())
    }

    private val _scheduledMatches: MutableStateFlow<MatchesState> =
        MutableStateFlow(MatchesState.Loading)
    val scheduledMatches = _scheduledMatches.convertMutableToImmutable(viewModelScope)

    private val _todayMatches: MutableStateFlow<MatchesState> =
        MutableStateFlow(MatchesState.Loading)
    val todayMatches = _todayMatches.convertMutableToImmutable(viewModelScope)

    private fun fetchAllMatchesData(dateFrom: String,dateTo: String = "2024-05-19") {
        viewModelScope.launch {
            val scheduledMatchesResponse =getTodayMatches.invoke(dateFrom,dateTo)
            _scheduledMatches.validateResponse(scheduledMatchesResponse)
        }
    }

    private fun fetchTodayMatchesData(dateFrom: String, dateTo: String) {
        viewModelScope.launch {
            val todayMatchesResponse = getTodayMatches.invoke(dateFrom, dateTo)
            _todayMatches.validateResponse(todayMatchesResponse)
        }
    }

    private fun MutableStateFlow<MatchesState>.validateResponse(response: ResultWorker<Response>) {
        when (response) {
            is ResultWorker.Error -> {
                val errorResponse = Gson().fromJson(response.e,ErrorResponse::class.java)
                updateState(this, MatchesState.Error(errorResponse.message))
            }

            ResultWorker.Loading -> {
                updateState(this, MatchesState.Loading)
            }

            is ResultWorker.Success -> {
                updateState(this, MatchesState.Success(response.data))
            }
        }
    }

    private fun updateState(stateFlow: MutableStateFlow<MatchesState>, matchesState: MatchesState) {
        stateFlow.update {
            matchesState
        }
    }

}