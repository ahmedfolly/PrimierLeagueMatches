package com.example.primierleaguematches.di

import com.example.primierleaguematches.data.APIService
import com.example.primierleaguematches.data.MatchesRepoImp
import com.example.primierleaguematches.domain.repos.MatchesRepo
import com.example.primierleaguematches.domain.usecases.GetScheduledMatches
import com.example.primierleaguematches.domain.usecases.GetTodayMatchesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideGetScheduledMatches(matchesRepo: MatchesRepo): GetScheduledMatches {
        return GetScheduledMatches(matchesRepo)
    }

    @Provides
    fun provideMatchesRepo(apiService: APIService): MatchesRepo {
        return MatchesRepoImp(apiService)
    }

    @Provides
    fun provideGetTodayMatchesUseCase(matchesRepo: MatchesRepo):GetTodayMatchesUseCase{
        return GetTodayMatchesUseCase(matchesRepo)
    }
}