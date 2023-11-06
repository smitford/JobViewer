package ru.practicum.android.diploma.job.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.job.presentation.viewmodel.JobFragmentViewModel
import ru.practicum.android.diploma.job.sharing.data.SharingRepositoryImpl
import ru.practicum.android.diploma.job.sharing.domain.impl.SharingInteractorImpl
import ru.practicum.android.diploma.job.sharing.domain.interactor.SharingInteractor
import ru.practicum.android.diploma.job.sharing.domain.repository.SharingRepository

val jobModule = module {

    single<SharingRepository> {
        SharingRepositoryImpl(androidContext())
    }

    single<SharingInteractor> {
        SharingInteractorImpl(get())
    }

    viewModel {
        JobFragmentViewModel(sharingInteractor = get())
    }

}