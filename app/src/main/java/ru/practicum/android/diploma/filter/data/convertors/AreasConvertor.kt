package ru.practicum.android.diploma.filter.data.convertors

import ru.practicum.android.diploma.filter.data.models.AreaDto
import ru.practicum.android.diploma.filter.domain.models.Area

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

    fun convertAreasDtoToAreaList(areaDto: AreaDto): List<Area> {
        val areas = mutableListOf<Area>()

        if (areaDto.parentId == null){
            countryName = areaDto.name
            countryId = areaDto.id
        }
        if (areaDto.parentId != null){
            areas.add(
                Area(
                    name = areaDto.name,
                    id = areaDto.id,
                    countryName = countryName,
                    countryId = countryId
                )
            )
        }
        areaDto.areas.forEach { childArea ->
            areas.addAll(convertAreasDtoToAreaList(childArea))
        }
        return areas
    }

    fun convertAreasDtoListToAreaList(areaDtoList: List<AreaDto>): List<Area> {
        val resultList = mutableListOf<Area>()
        areaDtoList.forEach { areaDto ->
            resultList.addAll(convertAreasDtoToAreaList(areaDto))
        }
        return resultList
    }

}