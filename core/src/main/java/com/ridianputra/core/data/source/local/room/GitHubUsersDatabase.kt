package com.ridianputra.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ridianputra.core.data.source.local.entity.GitHubUsersEntity

@Database(entities = [GitHubUsersEntity::class], version = 1, exportSchema = false)
abstract class GitHubUsersDatabase : RoomDatabase() {

    abstract fun gitHubUsersDao(): GitHubUsersDao
}
