package com.ridianputra.core.data.source.local.room

import androidx.room.*
import com.ridianputra.core.data.source.local.entity.GitHubUsersEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GitHubUsersDao {

    @Query("SELECT * FROM githubusers")
    fun getAllUsers(): Flow<List<GitHubUsersEntity>>

    @Query("SELECT * FROM githubusers WHERE isFavorite = 1")
    fun getFavoriteUsers(): Flow<List<GitHubUsersEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<GitHubUsersEntity>)

    @Update
    fun updateFavoriteUser(users: GitHubUsersEntity)
}
