package ru.practicum.android.diploma.job.data.impl.mapper

import ru.practicum.android.diploma.job.data.mainmodels.JobDtoForScreenResponse
import ru.practicum.android.diploma.job.domain.models.JobForScreen

class JobForScreenMapper() {
    fun mapToJobForScreen(response: JobDtoForScreenResponse): JobForScreen {
        return JobForScreen(
            id = response.id,
            name = response.name,
            salaryFrom = response.salary?.from.toString(),
            employerUrl = response.employer?.url,
            employerName = response.employer?.name,
            area = response.area?.name,
            experience = response.experience?.name,
            employment = response.employment?.name,
            description = response.description,
            keySkills = response.key_skills,
            contactsName = response.contacts?.name,
            email = response.contacts?.email,
            phonesNumber = response.contacts?.phones
        )
    }
}