package com.ridianputra.core.domain.usecase

import com.ridianputra.core.data.Resource
import com.ridianputra.core.domain.model.GitHubUsers
import kotlinx.coroutines.flow.Flow

interface GitHubUsersUseCase {
    fun getAllUsers(): Flow<Resource<List<GitHubUsers>>>
    fun getFavoriteUsers(): Flow<List<GitHubUsers>>
    fun setFavoriteUser(user: GitHubUsers, state: Boolean)
}
