package com.ridianputra.core.domain.repository

import com.ridianputra.core.data.Resource
import com.ridianputra.core.domain.model.GitHubUsers
import kotlinx.coroutines.flow.Flow

interface IGitHubUsersRepository {
    fun getAllUsers(): Flow<Resource<List<GitHubUsers>>>

    fun getFavoriteUsers(): Flow<List<GitHubUsers>>

    fun setFavoriteUser(users: GitHubUsers, state: Boolean)
}
