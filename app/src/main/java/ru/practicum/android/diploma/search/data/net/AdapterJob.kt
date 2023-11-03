package ru.practicum.android.diploma.search.data.net

import ru.practicum.android.diploma.search.data.models.JobInfoDto
import ru.practicum.android.diploma.search.data.models.JobSearchRequest
import ru.practicum.android.diploma.search.data.models.Salary
import ru.practicum.android.diploma.search.domain.models.Filter
import ru.practicum.android.diploma.search.domain.models.Job

object AdapterJob {

    fun jobInfoDtoToJobInfo(response: List<JobInfoDto>): List<Job> =  response.map {
        Job(
            id =it.id,
            area = it.area.name,
            department = it.department.name,
            employerImgUrl = it.employer.url,
            employer = it.employer.name,
            name = it.name,
            salary = formSalaryString(it.salary),
            type = it.type.name
        )
    }


    fun filterToJobReq(filter: Filter)= JobSearchRequest(
        page = 0,
        perPage = 10,
        text = filter.request,
        area = filter.area,
        industry = filter.industry,
        salary = filter.salary,
        onlyWithSalary = filter.onlyWithSalary
    )

    private fun formSalaryString(salary: Salary): String{
        return "от  ${salary.from.toString()}  до  ${salary.to.toString()} ${salary.currency}"
    }
}