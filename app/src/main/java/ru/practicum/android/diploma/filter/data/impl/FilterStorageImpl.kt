package ru.practicum.android.diploma.filter.data.impl

import android.content.SharedPreferences
import com.google.gson.Gson
import ru.practicum.android.diploma.filter.data.FilterStorage
import ru.practicum.android.diploma.filter.data.models.CountryDto
import ru.practicum.android.diploma.filter.data.models.FilterParametersDto
import ru.practicum.android.diploma.util.DataUtils.Companion.FILTER_COUNTRY
import ru.practicum.android.diploma.util.DataUtils.Companion.FILTER_SETTINGS

class FilterStorageImpl(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : FilterStorage {

    override fun saveFilterSettings(filterParameters: FilterParametersDto) {
        val jSON = gson.toJson(filterParameters)
        sharedPreferences
            .edit()
            .putString(FILTER_SETTINGS, jSON)
            .apply()
    }

    override fun getFilterSettings(): FilterParametersDto {
        val jSON = sharedPreferences.getString(FILTER_SETTINGS, "")
        return if (jSON.isNullOrEmpty()) {
            FilterParametersDto(
                country = null,
                areaId = null,
                area = null,
                industryId = null,
                industry = null,
                salary = null,
                onlyWithSalary = false
            )
        } else {
            gson.fromJson(jSON, FilterParametersDto::class.java)
        }
    }

    override fun clearFilterSettings() {
        sharedPreferences
            .edit()
            .remove(FILTER_SETTINGS)
            .apply()
    }

    private fun saveCountry(country: CountryDto) {
        val jSON = gson.toJson(country)
        sharedPreferences
            .edit()
            .putString(FILTER_COUNTRY, jSON)
            .apply()
    }

    override fun getCountry():CountryDto? {
        val jSON = sharedPreferences.getString(FILTER_COUNTRY, "")
        return if (jSON.isNullOrEmpty()) {
            null
        } else {
            gson.fromJson(jSON,CountryDto::class.java)
        }
    }

    private fun clearCountry() {
        sharedPreferences
            .edit()
            .remove(FILTER_COUNTRY)
            .apply()
    }

    override fun saveCountryToFilter(country: CountryDto) {
        val filterParam = getFilterSettings()

        if (filterParam.country != country.name) {
            saveFilterSettings(
                filterParam.copy(
                    country = country.name,
                    areaId = country.id,
                    area = null
                )
            )
            saveCountry(country)
        }
    }

    override fun clearCountryInFilter(){
        val filterParam = getFilterSettings()
        saveFilterSettings(
            filterParam.copy(
                country = null,
                areaId = null,
                area = null
            )
        )
        clearCountry()
    }


}