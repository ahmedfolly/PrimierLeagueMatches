package com.example.primierleaguematches.domain.models

data class Competition(
    val code: String,
    val emblem: String,
    val id: Int,
    val name: String,
    val type: String
)