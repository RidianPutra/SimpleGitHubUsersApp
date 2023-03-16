package com.ridianputra.simplegithubusersapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ridianputra.core.domain.usecase.GitHubUsersUseCase

class HomeViewModel(gitHubUsersUseCase: GitHubUsersUseCase) : ViewModel() {
    val gitHubUsers = gitHubUsersUseCase.getAllUsers().asLiveData()
}
