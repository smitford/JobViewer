package ru.practicum.android.diploma.favorite.data.db.mapper

import ru.practicum.android.diploma.favorite.data.db.entity.FavoriteEntity
import ru.practicum.android.diploma.job.domain.models.JobForScreen
import ru.practicum.android.diploma.search.domain.models.Job

class JobMapper {

    fun map(job: JobForScreen): FavoriteEntity {

        return FavoriteEntity(
            job.id.toString(),
            job.name,
            job.salaryFrom,
            job.employerLogoUrl,
            job.employerName,
            job.area,
            job.experience,
            job.employment,
            job.description,
            job.contactsName,
            job.email
        )

    }

    fun map(job: FavoriteEntity): JobForScreen {

        return JobForScreen(
            job.id,
            job.name,
            job.salaryFrom,
            job.employerLogoUrl,
            job.employerName,
            job.area,
            job.experience,
            job.employment,
            job.description,
            emptyArray(),
            job.contactsName,
            job.email,
            emptyArray()
        )

    }

    fun mapJob(job: FavoriteEntity): Job {
        return Job(
            job.id,
            job.area?: "",
            job.employment?: "",
            job.employerLogoUrl?: "",
            job.employerName?: "",
            job.name?: "",
            job.salaryFrom?: "",
            job.name ?: ""
        )

    }

}