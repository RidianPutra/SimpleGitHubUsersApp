package com.ridianputra.core.data.source.remote

import android.util.Log
import com.ridianputra.core.data.source.remote.network.ApiResponse
import com.ridianputra.core.data.source.remote.network.ApiService
import com.ridianputra.core.data.source.remote.response.UsersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllUsers(): Flow<ApiResponse<List<UsersResponse>>> {
        return flow {
            try {
                val response = apiService.getUsers()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
