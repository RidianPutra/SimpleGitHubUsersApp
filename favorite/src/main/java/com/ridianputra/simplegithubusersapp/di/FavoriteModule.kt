package com.ridianputra.simplegithubusersapp.di

import com.ridianputra.simplegithubusersapp.favorite.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}
