package ru.practicum.android.diploma.similarjob.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.similarjob.data.SimilarJobRepositoryImpl
import ru.practicum.android.diploma.similarjob.domain.SimilarJobInteractorImpl
import ru.practicum.android.diploma.similarjob.domain.api.SimilarJobRepository
import ru.practicum.android.diploma.similarjob.presentation.SimilarJobViewModel
import ru.practicum.android.diploma.similarjob.presentation.api.SimilarJobInteractor

val similarJobModule = module {
    viewModel { (id: String) ->
        SimilarJobViewModel(get(), id)
    }

    single<SimilarJobInteractor> {
        SimilarJobInteractorImpl(get())
    }

    single<SimilarJobRepository> {
        SimilarJobRepositoryImpl(get(), get())
    }
}