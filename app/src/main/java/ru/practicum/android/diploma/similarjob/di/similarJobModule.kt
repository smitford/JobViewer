package ru.practicum.android.diploma.similarjob.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.similarjob.presentation.SimilarJobViewModel

val similarJobModule = module {

    viewModel {
        SimilarJobViewModel(get())
    }

}