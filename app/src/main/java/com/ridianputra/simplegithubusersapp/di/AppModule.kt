package com.ridianputra.simplegithubusersapp.di

import com.ridianputra.core.domain.usecase.GitHubUsersInteractor
import com.ridianputra.core.domain.usecase.GitHubUsersUseCase
import com.ridianputra.simplegithubusersapp.detail.DetailUsersViewModel
import com.ridianputra.simplegithubusersapp.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel

import org.koin.dsl.module

val useCaseModule = module {
    factory<GitHubUsersUseCase> { GitHubUsersInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailUsersViewModel(get()) }
}
