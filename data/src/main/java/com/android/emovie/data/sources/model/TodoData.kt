package com.android.emovie.data.sources.model

data class TodoData(
    val id: Int,
    val userId: String,
    val title: String,
    val completed: Boolean
)