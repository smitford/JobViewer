package ru.practicum.android.diploma.filter.data.convertors

import ru.practicum.android.diploma.filter.data.models.AreaDto
import ru.practicum.android.diploma.filter.domain.models.Area

object AreasConvertor {

//    fun areasResponseToAreaDto(allAreasResponse: AllAreasResponse): AreaDto {
//        return AreaDto(
//            name = allAreasResponse.name,
//            id = allAreasResponse.id.toString(),
//            parentId = allAreasResponse.parentId.toString(),
//            areas = allAreasResponse.areas
//        )
//    }

    fun convertAreasDtoToAreaList(areaDto: AreaDto, parentName: String? = null): List<Area> {
        val areas = mutableListOf<Area>()
        if (areaDto.parentId != null) {
            areas.add(
                Area(
                    name = areaDto.name,
                    id = areaDto.id,
                    parentName = parentName ?: "No parent id",
                    parentId = areaDto.parentId
                )
            )
        }
        areaDto.areas.forEach { childArea ->
            areas.addAll(convertAreasDtoToAreaList(childArea, areaDto.name))
        }
        return areas
    }

    fun convertAreasDtoListToAreaList(areaDtoList: List<AreaDto>): List<Area> {
        val resultList = mutableListOf<Area>()
        areaDtoList.forEach { areaDto ->
            resultList.addAll(convertAreasDtoToAreaList(areaDto, areaDto.name))
        }
        return resultList
    }
}