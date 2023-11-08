package ru.practicum.android.diploma.job.data.mapper

import ru.practicum.android.diploma.job.data.mainmodels.JobDtoForScreenRequest
import ru.practicum.android.diploma.job.domain.models.JobForScreen
import ru.practicum.android.diploma.job.domain.models.JobForScreenInfo

object JobForScreenMapper {
    fun mapToJobForScreen(response: JobForScreenInfo): JobForScreen {
       return JobForScreen(
           id = response.job!!.id,
           name = response.job.name,
           salary = response.job.salary,
           employer = response.job.employer,
           area = response.job.area,
           experience = response.job.experience,
           employment = response.job.employment,
           description = response.job.description,
           keySkills = response.job.keySkills,
           contacts = response.job.contacts,
        )
    }
}