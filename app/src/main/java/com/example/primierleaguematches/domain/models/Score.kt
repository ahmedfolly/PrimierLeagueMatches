package com.example.primierleaguematches.domain.models

data class Score(
    val duration: String,
    val fullTime: FullTime,
    val halfTime: HalfTime,
    val winner: Any
)