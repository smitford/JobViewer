package ru.practicum.android.diploma.filter.data.convertors

import ru.practicum.android.diploma.filter.data.models.FilterParametersDto
import ru.practicum.android.diploma.filter.domain.models.FilterParameters

object FilterParametersConvertor {
    fun filterParamDtoToFilterParam(filterParametersDto: FilterParametersDto): FilterParameters {
        return FilterParameters(
            country = filterParametersDto.country,
            areaId = filterParametersDto.areaId,
            area = filterParametersDto.area,
            industryId = filterParametersDto.industryId,
            industry = filterParametersDto.industry,
            salary = filterParametersDto.salary,
            onlyWithSalary = filterParametersDto.onlyWithSalary
        )
    }

    fun filterParamToFilterParamDto(filterParameters: FilterParameters): FilterParametersDto {
        return FilterParametersDto(
            country = filterParameters.country,
            areaId = filterParameters.areaId,
            area = filterParameters.area,
            industryId = filterParameters.industryId,
            industry = filterParameters.industry,
            salary = filterParameters.salary,
            onlyWithSalary = filterParameters.onlyWithSalary
        )
    }
}