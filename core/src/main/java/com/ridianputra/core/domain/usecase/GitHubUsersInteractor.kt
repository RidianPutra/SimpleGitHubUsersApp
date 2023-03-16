package com.ridianputra.core.domain.usecase

import com.ridianputra.core.data.Resource
import com.ridianputra.core.domain.model.GitHubUsers
import com.ridianputra.core.domain.repository.IGitHubUsersRepository
import kotlinx.coroutines.flow.Flow

class GitHubUsersInteractor(private val gitHubUsersRepository: IGitHubUsersRepository) :
    GitHubUsersUseCase {

    override fun getAllUsers(): Flow<Resource<List<GitHubUsers>>> =
        gitHubUsersRepository.getAllUsers()

    override fun getFavoriteUsers(): Flow<List<GitHubUsers>> =
        gitHubUsersRepository.getFavoriteUsers()

    override fun setFavoriteUser(user: GitHubUsers, state: Boolean) =
        gitHubUsersRepository.setFavoriteUser(user, state)
}
