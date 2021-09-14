package com.shubhamkumarwinner.composektor.data.domain.use_case

import com.shubhamkumarwinner.composektor.data.domain.model.Post
import com.shubhamkumarwinner.composektor.data.domain.repository.PostRepository
import com.shubhamkumarwinner.composektor.util.ApiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.HttpRetryException
import javax.inject.Inject

class PostUseCase @Inject constructor(private val repository: PostRepository) {

    operator fun invoke(): Flow<ApiState<List<Post>>> = flow {
        try {
            emit(ApiState.Loading())
            val coins = repository.getPost()
            emit(ApiState.Success<List<Post>>(coins))
        }catch (e:HttpRetryException){
            emit(ApiState.Error<List<Post>>(e.localizedMessage?:"An unexpected error occurred"))
        }catch (e: IOException){
            emit(ApiState.Error<List<Post>>("Couldn't reach server. Check your internet connection"))

        }
    }
}