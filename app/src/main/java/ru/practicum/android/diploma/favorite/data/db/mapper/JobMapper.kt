package ru.practicum.android.diploma.favorite.data.db.mapper

import ru.practicum.android.diploma.favorite.data.db.entity.FavoriteEntity
import ru.practicum.android.diploma.favorite.data.db.entity.KeySkillsEntity
import ru.practicum.android.diploma.favorite.data.db.entity.PhonesEntity
import ru.practicum.android.diploma.job.data.secondarymodels.Phones
import ru.practicum.android.diploma.job.data.secondarymodels.Skills
import ru.practicum.android.diploma.job.domain.models.JobForScreen
import ru.practicum.android.diploma.search.domain.models.Vacancy

class JobMapper {

    fun map(job: JobForScreen): FavoriteEntity {

        return FavoriteEntity(
            job.id.toString(),
            job.name,
            job.salaryFrom,
            job.employerLogoUrl,
            job.employerUrl,
            job.employerName,
            job.area,
            job.address,
            job.experience,
            job.employment,
            job.description,
            job.contactsName,
            job.email
        )

    }

    fun map(job: FavoriteEntity,skills : Array<Skills?>,phones: Array<Phones?>): JobForScreen {

        return JobForScreen(
            job.id,
            job.name,
            job.salaryFrom,
            job.employerLogoUrl,
            job.employerUrl,
            job.employerName,
            job.area,
            job.address,
            job.experience,
            job.employment,
            job.description,
            skills,
            job.contactsName,
            job.email,
            phones
        )

    }

    fun mapJob(job: FavoriteEntity): Vacancy {
        return Vacancy(
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

    fun mapSkills(skill:Skills,idVacancy: String) : KeySkillsEntity{
        return KeySkillsEntity(idVacancy = idVacancy, name = skill.name)
    }

    fun mapSkills(keySkillsEntity: KeySkillsEntity) : Skills{
        return Skills(keySkillsEntity.name)
    }

    fun mapPhones(phones: Phones,idVacancy: String): PhonesEntity{
        return PhonesEntity(idVacancy=idVacancy, comment = phones.comment, formatted = phones.formatted)
    }

    fun mapPhones(phonesEntity: PhonesEntity): Phones{
        return Phones(phonesEntity.comment,phonesEntity.formatted)
    }

}