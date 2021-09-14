package com.shubhamkumarwinner.composektor.data.domain.repository

import com.shubhamkumarwinner.composektor.data.domain.model.Post
import com.shubhamkumarwinner.composektor.data.network.ApiService
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val apiService: ApiService):PostRepository {
    override suspend fun getPost(): List<Post> = apiService.getPost()
}