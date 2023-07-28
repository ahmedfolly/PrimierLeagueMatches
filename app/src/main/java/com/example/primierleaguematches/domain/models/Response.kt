package com.example.primierleaguematches.domain.models

data class Response(
    val competition: Competition,
    val filters: Filters,
    val matches: List<Matche>,
    val resultSet: ResultSet
)