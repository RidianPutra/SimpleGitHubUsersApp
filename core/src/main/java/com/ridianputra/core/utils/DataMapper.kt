package com.ridianputra.core.utils

import com.ridianputra.core.data.source.local.entity.GitHubUsersEntity
import com.ridianputra.core.data.source.remote.response.UsersResponse
import com.ridianputra.core.domain.model.GitHubUsers

object DataMapper {
    fun mapResponsesToEntities(input: List<UsersResponse>): List<GitHubUsersEntity> {
        val usersList = ArrayList<GitHubUsersEntity>()
        input.map {
            val user = GitHubUsersEntity(
                login = it.login,
                id = it.id,
                nodeId = it.nodeId,
                avatarUrl = it.avatarUrl,
                gravatarId = it.gravatarId,
                url = it.url,
                htmlUrl = it.htmlUrl,
                followersUrl = it.followersUrl,
                followingUrl = it.followingUrl,
                gistsUrl = it.gistsUrl,
                starredUrl = it.starredUrl,
                subscriptionsUrl = it.subscriptionsUrl,
                organizationsUrl = it.organizationsUrl,
                reposUrl = it.reposUrl,
                eventsUrl = it.eventsUrl,
                receivedEventsUrl = it.receivedEventsUrl,
                type = it.type,
                siteAdmin = it.siteAdmin,
                isFavorite = false
            )
            usersList.add(user)
        }
        return usersList
    }

    fun mapEntitiesToDomain(input: List<GitHubUsersEntity>): List<GitHubUsers> =
        input.map {
            GitHubUsers(
                login = it.login,
                id = it.id,
                nodeId = it.nodeId,
                avatarUrl = it.avatarUrl,
                gravatarId = it.gravatarId,
                url = it.url,
                htmlUrl = it.htmlUrl,
                followersUrl = it.followersUrl,
                followingUrl = it.followingUrl,
                gistsUrl = it.gistsUrl,
                starredUrl = it.starredUrl,
                subscriptionsUrl = it.subscriptionsUrl,
                organizationsUrl = it.organizationsUrl,
                reposUrl = it.reposUrl,
                eventsUrl = it.eventsUrl,
                receivedEventsUrl = it.receivedEventsUrl,
                type = it.type,
                siteAdmin = it.siteAdmin,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: GitHubUsers) = GitHubUsersEntity(
        login = input.login,
        id = input.id,
        nodeId = input.nodeId,
        avatarUrl = input.avatarUrl,
        gravatarId = input.gravatarId,
        url = input.url,
        htmlUrl = input.htmlUrl,
        followersUrl = input.followersUrl,
        followingUrl = input.followingUrl,
        gistsUrl = input.gistsUrl,
        starredUrl = input.starredUrl,
        subscriptionsUrl = input.subscriptionsUrl,
        organizationsUrl = input.organizationsUrl,
        reposUrl = input.reposUrl,
        eventsUrl = input.eventsUrl,
        receivedEventsUrl = input.receivedEventsUrl,
        type = input.type,
        siteAdmin = input.siteAdmin,
        isFavorite = input.isFavorite
    )
}
