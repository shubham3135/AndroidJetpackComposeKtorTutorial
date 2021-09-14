package com.shubhamkumarwinner.composektor.data.network

import com.shubhamkumarwinner.composektor.data.domain.model.Post
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*

class ApiService {

    private val client = HttpClient(Android){
        install(DefaultRequest){
            headers.append("Content-Type", "application/json")
        }
        install(JsonFeature){
            serializer = GsonSerializer()
        }
        engine {
            connectTimeout = 100_000
            socketTimeout = 100_000
        }
    }

    suspend fun getPost() : List<Post> {
        return client.get {
            url("https://jsonplaceholder.typicode.com/posts")
        }
    }
}
