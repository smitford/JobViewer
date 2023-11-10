package ru.practicum.android.diploma.job.data.impl.mapper

import ru.practicum.android.diploma.job.data.mainmodels.JobDtoForScreenResponse
import ru.practicum.android.diploma.job.domain.models.JobForScreen
import ru.practicum.android.diploma.util.ResourceProvider
import ru.practicum.android.diploma.util.TextUtils

class JobForScreenMapper(private val resourceProvider: ResourceProvider) {
    fun mapToJobForScreen(response: JobDtoForScreenResponse): JobForScreen {
        return JobForScreen(
            id = response.id,
            name = response.name,
            salaryFrom = TextUtils.checkSalaryBorder(response.salary, resourceProvider),
            employerLogoUrl = response.employer?.logo_urls?.mediumIcon,
            employerName = response.employer?.name,
            area = response.area?.name,
            experience = response.experience?.name,
            employment = response.employment?.name,
            description = response.description,
            keySkills = response.key_skills,
            contactsName = response.contacts?.name,
            email = response.contacts?.email
        )
    }
}