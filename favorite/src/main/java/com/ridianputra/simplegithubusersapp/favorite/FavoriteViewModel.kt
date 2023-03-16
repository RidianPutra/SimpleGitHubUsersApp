package com.ridianputra.simplegithubusersapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ridianputra.core.domain.usecase.GitHubUsersUseCase

class FavoriteViewModel(gitHubUsersUseCase: GitHubUsersUseCase) : ViewModel() {
    val favoriteUsers = gitHubUsersUseCase.getFavoriteUsers().asLiveData()
}
