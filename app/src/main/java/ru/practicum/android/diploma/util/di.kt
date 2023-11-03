package ru.practicum.android.diploma.util

import org.koin.dsl.module

val utilDi = module {
    single<NetworkClient> {
        RetrofitNetworkClient(context = get())
    }
}