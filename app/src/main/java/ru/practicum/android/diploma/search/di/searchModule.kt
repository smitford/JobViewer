package ru.practicum.android.diploma.search.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.search.data.net.AdapterJob
import ru.practicum.android.diploma.search.data.net.JobNetworkRepositoryImp
import ru.practicum.android.diploma.search.data.sheared_pref.FilterShPrefRepositoryImp
import ru.practicum.android.diploma.search.domain.api.FilterShPrefRepository
import ru.practicum.android.diploma.search.domain.api.JobNetworkRepository
import ru.practicum.android.diploma.search.domain.use_cases.GetSearchFilterUseCase
import ru.practicum.android.diploma.search.domain.use_cases.LoadJobsUseCase
import ru.practicum.android.diploma.search.domain.use_cases.impl.GetSearchFilterUseCaseImp
import ru.practicum.android.diploma.search.domain.use_cases.impl.LoadJobsUseCaseImp
import ru.practicum.android.diploma.search.presentation.SearchViewModel


val searchModule = module {
    single<FilterShPrefRepository> {
        FilterShPrefRepositoryImp(get(), get())
    }

    single<JobNetworkRepository> {
        JobNetworkRepositoryImp(get(), get())
    }

    factory<LoadJobsUseCase> {
        LoadJobsUseCaseImp(get())
    }

    factory<GetSearchFilterUseCase> {
        GetSearchFilterUseCaseImp(get())
    }

    viewModel {
        SearchViewModel(
            get(),
            get()
        )
    }

    single { AdapterJob(get()) }
}


