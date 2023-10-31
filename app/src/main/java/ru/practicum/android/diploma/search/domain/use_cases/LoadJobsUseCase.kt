package ru.practicum.android.diploma.search.domain.use_cases

import ru.practicum.android.diploma.search.domain.models.JobInfo

interface LoadJobsUseCase {
    fun execute(term: String): List<JobInfo>
}