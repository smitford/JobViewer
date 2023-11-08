package ru.practicum.android.diploma.job.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.favorite.data.db.AppDataBase
import ru.practicum.android.diploma.favorite.data.impl.JobFavoriteRepositoryImpl
import ru.practicum.android.diploma.favorite.domain.api.JobFavoriteRepository
import ru.practicum.android.diploma.favorite.domain.impl.JobFavoriteInteractorImpl
import ru.practicum.android.diploma.favorite.presentation.api.JobFavoriteInteractor
import ru.practicum.android.diploma.job.data.repositoryimpl.JobRepositoryImpl
import ru.practicum.android.diploma.job.domain.api.JobRepository
import ru.practicum.android.diploma.job.domain.usecases.impl.LoadJobUseCase
import ru.practicum.android.diploma.job.domain.usecases.impl.LoadJobUseCaseImpl
import ru.practicum.android.diploma.job.presentation.viewmodel.JobFragmentViewModel
import ru.practicum.android.diploma.job.sharing.data.SharingRepositoryImpl
import ru.practicum.android.diploma.job.sharing.domain.impl.SharingInteractorImpl
import ru.practicum.android.diploma.job.sharing.domain.interactor.SharingInteractor
import ru.practicum.android.diploma.job.sharing.domain.repository.SharingRepository
import ru.practicum.android.diploma.search.presentation.SearchViewModel

val jobModule = module {

    single {
        Room.databaseBuilder(androidContext(), AppDataBase::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single<JobFavoriteRepository> {
        JobFavoriteRepositoryImpl(get())
    }

    single<JobFavoriteInteractor> {
        JobFavoriteInteractorImpl(get())
    }

    single<SharingRepository> {
        SharingRepositoryImpl(androidContext())
    }

    single<SharingInteractor> {
        SharingInteractorImpl(get())
    }

    single<JobRepository> {
        JobRepositoryImpl(networkClient = get())
    }

    factory<LoadJobUseCase> {
        LoadJobUseCaseImpl(repository = get())
    }

    viewModel {
        JobFragmentViewModel(
            sharingInteractor = get(),
            loadJobUseCaseImpl = get()
        )
    }

}