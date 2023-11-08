package ru.practicum.android.diploma.filter.di

import android.app.Application
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.practicum.android.diploma.filter.data.FilterStorage
import ru.practicum.android.diploma.filter.data.impl.FilterStorageImpl
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
}