package ru.practicum.android.diploma.search.domain.use_cases.impl

import ru.practicum.android.diploma.search.domain.api.NetworkRepository
import ru.practicum.android.diploma.search.domain.models.JobInfo
import ru.practicum.android.diploma.search.domain.use_cases.LoadJobsUseCase

class LoadJobsUseCaseImp(repository: NetworkRepository) : LoadJobsUseCase {
    override fun execute(term: String): List<JobInfo> {
        TODO("Not yet implemented")
    }
}