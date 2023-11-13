package ru.practicum.android.diploma.job.data.impl.mapper

import ru.practicum.android.diploma.job.data.mainmodels.JobDtoForScreenResponse
import ru.practicum.android.diploma.job.data.secondarymodels.Skills
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
            employerUrl = response.employer?.alternate_url,
            employerName = response.employer?.name,
            area = response.area?.name,
            address = TextUtils.employerAddressToString(response.address, response.area?.name),
            experience = response.experience?.name,
            employment = response.employment?.name,
            description = response.description,
            keySkills = response.key_skills.toTypedArray(),
            contactsName = response.contacts?.name,
            email = response.contacts?.email,
            phones = response.contacts?.phones?.toTypedArray()
        )
    }
}