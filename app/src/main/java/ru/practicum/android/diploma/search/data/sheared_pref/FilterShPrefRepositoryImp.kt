package ru.practicum.android.diploma.search.data.sheared_pref

import android.content.SharedPreferences
import com.google.gson.Gson
import ru.practicum.android.diploma.search.data.models.FilterSp
import ru.practicum.android.diploma.search.domain.api.FilterShPrefRepository
import ru.practicum.android.diploma.search.domain.models.Filter
import ru.practicum.android.diploma.util.DataUtils


class FilterShPrefRepositoryImp(
    private val sharePref: SharedPreferences,
    private val gson: Gson
) :
    FilterShPrefRepository {
    override fun getFilter(): Filter {
        val filterString = sharePref.getString(DataUtils.FILTER_SETTINGS, null)
        val filter = try {
            gson.fromJson(
                filterString.toString(),
                FilterSp::class.java,
            )
        } catch (e: Exception) {
            FilterSp()
        }
        return AdapterFilter.filterSpToFilter(filter = filter)
    }
}