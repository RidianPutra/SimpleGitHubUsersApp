package com.ridianputra.simplegithubusersapp.detail

import androidx.lifecycle.ViewModel
import com.ridianputra.core.domain.model.GitHubUsers
import com.ridianputra.core.domain.usecase.GitHubUsersUseCase

class DetailUsersViewModel(private val gitHubUsersUseCase: GitHubUsersUseCase) : ViewModel() {
    fun setFavoriteUser(user: GitHubUsers, newStatus: Boolean) =
        gitHubUsersUseCase.setFavoriteUser(user, newStatus)
}
