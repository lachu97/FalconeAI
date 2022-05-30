package com.example.falconeai.data.models

data class auth(
    val token: String
)

data class request(
    val token: String,
    val planet_names: List<String>,
    val vehicle_names: List<String>
)

data class response(
    val planet_name: String,
    val status: String,
    val error: String?
)