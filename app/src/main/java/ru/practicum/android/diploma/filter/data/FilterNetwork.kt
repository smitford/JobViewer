package ru.practicum.android.diploma.filter.data

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filter.data.models.AreaDto
import ru.practicum.android.diploma.filter.domain.models.Country
import ru.practicum.android.diploma.search.domain.api.DtoConsumer

interface FilterNetwork {
    suspend fun getCountries(): Flow<DtoConsumer<List<Country>>>
    suspend fun getAllArea(): Flow<DtoConsumer<List<AreaDto>>>
}