package com.example.primierleaguematches.domain.models

data class Filters(
    val season: String,
    val status: List<String>
)