package ru.practicum.android.diploma.filter.data.convertors

import ru.practicum.android.diploma.filter.data.models.IndustryDto
import ru.practicum.android.diploma.filter.domain.models.Industry

object IndustryConvertor {

    private fun industryDtoToIndustry(industry: IndustryDto): Industry {
        return Industry(
            id = industry.id,
            name = industry.name
        )
    }

    fun industryDtoListToIndustryList(industryDtoList: List<IndustryDto>): List<Industry> {
        val industries = mutableListOf<Industry>()

        industryDtoList.forEach { industryDto ->
            industries.add(industryDtoToIndustry(industryDto))
            industryDto.industries?.forEach { industries.add(industryDtoToIndustry(it)) }
        }
        industries.sortBy { it.name }
        return industries
    }
}