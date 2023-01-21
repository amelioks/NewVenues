package com.ameliok.newvenues.data

data class Filter(
    val id: String,
    val name: String,
    val type: String,
    val values: List<Value>
)