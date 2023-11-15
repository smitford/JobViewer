package ru.practicum.android.diploma.filter.data.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.practicum.android.diploma.filter.data.FilterNetwork
import ru.practicum.android.diploma.filter.data.convertors.CountryConvertor
import ru.practicum.android.diploma.filter.data.models.AreaDto
import ru.practicum.android.diploma.filter.data.models.AllAreasResponse
import ru.practicum.android.diploma.filter.data.models.CountriesResponse
import ru.practicum.android.diploma.filter.data.models.FilterRequest
import ru.practicum.android.diploma.filter.domain.models.Country
import ru.practicum.android.diploma.search.data.models.ResultCodes
import ru.practicum.android.diploma.search.domain.api.DtoConsumer
import ru.practicum.android.diploma.util.NetworkClient


class FilterNetworkImpl(private val networkClient: NetworkClient) : FilterNetwork {

    override suspend fun getCountries(): Flow<DtoConsumer<List<Country>>> = flow {

        val response = networkClient.doRequest(FilterRequest.Countries)

        when (response.responseCode) {
            ResultCodes.NO_NET_CONNECTION -> {
                emit(DtoConsumer.NoInternet(response.responseCode.code))
            }

            ResultCodes.ERROR -> {
                emit(DtoConsumer.Error(response.responseCode.code))
            }

            ResultCodes.SUCCESS -> {
                val listResponse = response as CountriesResponse
                val data = listResponse.results.map {
                    CountryConvertor.countryDtoToCountry(it)
                }
                emit(DtoConsumer.Success(data))
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getAllArea(): Flow<DtoConsumer<List<AreaDto>>> = flow {
        val response = networkClient.doRequest(FilterRequest.Areas)
        when (response.responseCode) {
            ResultCodes.NO_NET_CONNECTION -> {
                emit(DtoConsumer.NoInternet(response.responseCode.code))
            }

            ResultCodes.ERROR -> {
                emit(DtoConsumer.Error(response.responseCode.code))
            }

            ResultCodes.SUCCESS -> {
                val areas = response as AllAreasResponse
                val data = areas.results
                emit(DtoConsumer.Success(data))
            }
        }
    }.flowOn(Dispatchers.IO)
}