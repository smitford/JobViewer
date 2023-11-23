package ru.practicum.android.diploma.search.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.search.data.net.JobNetworkRepositoryImp
import ru.practicum.android.diploma.search.domain.api.JobNetworkRepository
import ru.practicum.android.diploma.search.domain.use_cases.LoadJobsUseCase
import ru.practicum.android.diploma.search.domain.use_cases.impl.LoadJobsUseCaseImp
import ru.practicum.android.diploma.search.presentation.SearchViewModel

val searchModule = module {


    single<JobNetworkRepository> {
        JobNetworkRepositoryImp(networkClient = get())
    }

    factory<LoadJobsUseCase> {
        LoadJobsUseCaseImp(repository = get())
    }

    viewModel {
        SearchViewModel(
            filter = get(),
            loadJobsUseCase = get()
        )
    }
}


