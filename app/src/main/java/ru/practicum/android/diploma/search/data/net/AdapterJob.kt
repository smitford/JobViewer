package ru.practicum.android.diploma.search.data.net

import ru.practicum.android.diploma.search.data.models.JobInfoDto
import ru.practicum.android.diploma.search.data.models.JobSearchRequest
import ru.practicum.android.diploma.search.data.models.LogoUrls
import ru.practicum.android.diploma.search.data.models.Salary
import ru.practicum.android.diploma.search.domain.models.Filter
import ru.practicum.android.diploma.search.domain.models.Job

object AdapterJob {

    fun jobInfoDtoToJobInfo(response: List<JobInfoDto>): List<Job> = response.map {
        Job(
            id = it.id,
            area = it.area.name,
            department = it.department?.name ?: " ",
            employerImgUrl = getLogo(it.employer.logoUrls),
            employer = it.employer.name,
            name = it.name,
            salary = formSalaryString(it.salary),
            type = it.type.name ?: " "
        )
    }


    fun filterToJobReq(filter: Filter) = JobSearchRequest(
        makeHasMap(filter)
    )

    private fun getLogo(logoUrls: LogoUrls?): String {
        // if (logoUrls?.smallIcon != null) return logoUrls.smallIcon.toString()
        if (logoUrls?.mediumIcon != null) return logoUrls.mediumIcon.toString()
        if (logoUrls?.original != null) return logoUrls.original.toString()
        return " "
    }

    private fun formSalaryString(salary: Salary?): String {
        if (salary == null) return " "
        if (salary.from != null && salary.to != null)
            return "от  ${salary.from}  до  ${salary.to}"
        return if (salary.from != null)
            "от  ${salary.from}"
        else
            "до  ${salary.to}"
    }

    private fun makeHasMap(filter: Filter): HashMap<String, String> {
        val request = HashMap<String, String>()
        request["text"] = filter.request
        //request["page"] = filter.page.toString()
        // request["per_page"] = filter.request
        if (filter.area != null) request["area"] = filter.area
        if (filter.industry != null) request["industry"] = filter.industry
        if (filter.salary != null) request["area"] = filter.salary.toString()

        return request
    }
}

