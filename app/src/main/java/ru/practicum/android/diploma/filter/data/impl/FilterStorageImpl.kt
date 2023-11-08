package ru.practicum.android.diploma.filter.data.impl

import android.content.SharedPreferences
import com.google.gson.Gson
import ru.practicum.android.diploma.filter.data.FilterStorage
import ru.practicum.android.diploma.filter.domain.models.FilterParameters
import ru.practicum.android.diploma.util.DataUtils.Companion.FILTER_SETTINGS

class FilterStorageImpl(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : FilterStorage {

    override fun saveFilterSettings(filterParameters: FilterParameters) {
        val jSON = gson.toJson(filterParameters)
        sharedPreferences
            .edit()
            .putString(FILTER_SETTINGS, jSON)
            .apply()
    }

    override fun getFilterSettings(): FilterParameters {
        val jSON = sharedPreferences.getString(FILTER_SETTINGS, "")
        return if (jSON.isNullOrBlank()) {
            FilterParameters(
                country = null,
                areaId = null,
                area = null,
                industryId = null,
                industry = null,
                salary = null,
                onlyWithSalary = false
            )
        } else {
            gson.fromJson(jSON, FilterParameters::class.java)
        }
    }

    override fun clearFilterSettings() {
        sharedPreferences
            .edit()
            .remove(FILTER_SETTINGS)
            .apply()
    }

}