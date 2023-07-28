package com.example.primierleaguematches.domain.usecases

import com.example.primierleaguematches.domain.repos.MatchesRepo

class GetTodayMatchesUseCase(private val matchesRepo: MatchesRepo) {
    suspend operator fun invoke(dateFrom: String, dateTo: String) =
        matchesRepo.getTodayMatches(dateFrom, dateTo)
}