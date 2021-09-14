package com.shubhamkumarwinner.composektor.data.domain.repository

import com.shubhamkumarwinner.composektor.data.domain.model.Post

interface PostRepository {
    suspend fun getPost(): List<Post>
}