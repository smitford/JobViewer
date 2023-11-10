package ru.practicum.android.diploma.search.data.sheared_pref

import ru.practicum.android.diploma.search.data.models.FilterSp
import ru.practicum.android.diploma.search.domain.models.Filter

object AdapterFilter {
    fun filterSpToFilter(filter: FilterSp?) = Filter(
        area = filter?.areaId,
        industry = filter?.industryId,
        salary = filter?.salary?.toInt(),
        onlyWithSalary = filter?.onlyWithSalary ?: false
    )
}