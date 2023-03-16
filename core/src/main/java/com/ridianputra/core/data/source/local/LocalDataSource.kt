package com.ridianputra.core.data.source.local

import com.ridianputra.core.data.source.local.entity.GitHubUsersEntity
import com.ridianputra.core.data.source.local.room.GitHubUsersDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gitHubUsersDao: GitHubUsersDao) {
    fun getAllUsers(): Flow<List<GitHubUsersEntity>> = gitHubUsersDao.getAllUsers()

    fun getFavoriteUsers(): Flow<List<GitHubUsersEntity>> = gitHubUsersDao.getFavoriteUsers()

    suspend fun insertUsers(usersList: List<GitHubUsersEntity>) =
        gitHubUsersDao.insertUsers(usersList)

    fun setFavoriteUser(users: GitHubUsersEntity, newState: Boolean) {
        users.isFavorite = newState
        gitHubUsersDao.updateFavoriteUser(users)
    }
}
