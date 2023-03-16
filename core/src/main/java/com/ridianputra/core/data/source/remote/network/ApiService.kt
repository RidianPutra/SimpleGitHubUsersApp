package com.ridianputra.core.data.source.remote.network

import com.ridianputra.core.data.source.remote.response.ListUsersResponse
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): ListUsersResponse
}
