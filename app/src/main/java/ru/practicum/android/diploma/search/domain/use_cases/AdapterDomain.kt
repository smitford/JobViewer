package ru.practicum.android.diploma.search.domain.use_cases

import ru.practicum.android.diploma.search.domain.models.Codes
import ru.practicum.android.diploma.search.domain.models.JobsInfo

object AdapterDomain {
    fun codeMapper(codesError: Codes) = JobsInfo(responseCodes = codesError, null)
}