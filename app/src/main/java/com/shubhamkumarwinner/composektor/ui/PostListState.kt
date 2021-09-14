package com.shubhamkumarwinner.composektor.ui

import com.shubhamkumarwinner.composektor.data.domain.model.Post

data class PostListState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val error: String = ""
)