package ru.practicum.android.diploma.filter.data.convertors

import ru.practicum.android.diploma.filter.data.models.AreaDto
import ru.practicum.android.diploma.filter.data.models.CountryDto
import ru.practicum.android.diploma.filter.domain.models.Country
import ru.practicum.android.diploma.filter.domain.models.Region

object AreasConvertor {

    private var countryId =""
    private var countryName  = ""

//    fun areasResponseToAreaDto(allAreasResponse: AllAreasResponse): AreaDto {
//        return AreaDto(
//            name = allAreasResponse.name,
//            id = allAreasResponse.id.toString(),
//            parentId = allAreasResponse.parentId.toString(),
//            areas = allAreasResponse.areas
//        )
//    }

    fun convertAreasDtoToAreaList(areaDto: AreaDto): List<Region> {
        val regions = mutableListOf<Region>()

        if (areaDto.parentId == null){
            countryName = areaDto.name
            countryId = areaDto.id
        }
        if (areaDto.parentId != null){
            regions.add(
                Region(
                    name = areaDto.name,
                    id = areaDto.id,
                    countryName = countryName,
                    countryId = countryId
                )
            )
        }
        areaDto.areas.forEach { childArea ->
            regions.addAll(convertAreasDtoToAreaList(childArea))
        }
        return regions
    }

    fun convertAreasDtoListToAreaList(areaDtoList: List<AreaDto>): List<Region> {
        val resultList = mutableListOf<Region>()
        areaDtoList.forEach { areaDto ->
            resultList.addAll(convertAreasDtoToAreaList(areaDto))
        }
        return resultList
    }

    private fun areasDtoToCounty(areaDto: AreaDto) : Country {
        return Country(
            id = areaDto.id,
            name = areaDto.name
        )
    }

    fun areasDtoListToCountry(areaDtoList: List<AreaDto>): List<Country> {
        val countryList = areaDtoList.map { areaDto -> areasDtoToCounty(areaDto) }
        val convertedList = countryList.toMutableList()
        val countryToMove = convertedList.find { it.id == "1001" }
        convertedList.remove(countryToMove)
        if (countryToMove != null) {
            convertedList.add(countryToMove)
        }
        return convertedList
    }

}