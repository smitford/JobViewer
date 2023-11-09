package ru.practicum.android.diploma.filter.di

import android.app.Application
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.filter.data.FilterStorage
import ru.practicum.android.diploma.filter.data.impl.FilterRepositoryImpl
import ru.practicum.android.diploma.filter.data.impl.FilterStorageImpl
import ru.practicum.android.diploma.filter.domain.FilterInteractor
import ru.practicum.android.diploma.filter.domain.FilterRepository
import ru.practicum.android.diploma.filter.domain.impl.FilterInteractorImpl
import ru.practicum.android.diploma.filter.presentation.view_model.FilterSettingsViewModel
import ru.practicum.android.diploma.util.DataUtils.Companion.APP_SETTINGS

val filterModule = module {
    single {
        Gson()
    }

    single {
        androidContext().getSharedPreferences(
            APP_SETTINGS,
            Application.MODE_PRIVATE
        )
    }

    single <FilterStorage> {
        FilterStorageImpl(get(),get())
    }

    single <FilterRepository> {
        FilterRepositoryImpl(get())
    }

    single <FilterInteractor> {
        FilterInteractorImpl(get())
    }

    viewModel {
        FilterSettingsViewModel(get())
    }
}