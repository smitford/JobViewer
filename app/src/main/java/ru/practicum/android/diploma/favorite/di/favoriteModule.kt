package ru.practicum.android.diploma.favorite.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.favorite.data.FavoriteRepositoryImpl
import ru.practicum.android.diploma.favorite.domain.FavoriteInteractorImpl
import ru.practicum.android.diploma.favorite.domain.api.FavoriteRepository
import ru.practicum.android.diploma.favorite.presentation.FavoriteViewModel
import ru.practicum.android.diploma.favorite.presentation.api.FavoriteInteractor

val favoriteModule = module {

    viewModel {
        FavoriteViewModel(get())
    }

    single<FavoriteInteractor> {
        FavoriteInteractorImpl(get())
    }

    single<FavoriteRepository> {
        FavoriteRepositoryImpl()
    }

}