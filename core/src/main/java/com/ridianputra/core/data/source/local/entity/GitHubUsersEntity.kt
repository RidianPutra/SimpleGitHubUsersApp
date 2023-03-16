package com.ridianputra.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "githubusers")
data class GitHubUsersEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "login")
    var login: String,

    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "nodeId")
    var nodeId: String,

    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String,

    @ColumnInfo(name = "gravatarId")
    var gravatarId: String,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "htmlUrl")
    var htmlUrl: String,

    @ColumnInfo(name = "followersUrl")
    var followersUrl: String,

    @ColumnInfo(name = "followingUrl")
    var followingUrl: String,

    @ColumnInfo(name = "gistsUrl")
    var gistsUrl: String,

    @ColumnInfo(name = "starredUrl")
    var starredUrl: String,

    @ColumnInfo(name = "subscriptionsUrl")
    var subscriptionsUrl: String,

    @ColumnInfo(name = "organizationsUrl")
    var organizationsUrl: String,

    @ColumnInfo(name = "reposUrl")
    var reposUrl: String,

    @ColumnInfo(name = "eventsUrl")
    var eventsUrl: String,

    @ColumnInfo(name = "receivedEventsUrl")
    var receivedEventsUrl: String,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "siteAdmin")
    var siteAdmin: Boolean,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
