package ru.practicum.android.diploma.util

import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.util.Log
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.job.data.secondarymodels.Phones
import ru.practicum.android.diploma.job.data.secondarymodels.Skills
import ru.practicum.android.diploma.job.data.secondarymodels.Salary
import java.util.Objects


object TextUtils {

    fun checkSalaryBorder(salaryDto: Salary?, resourceProvider: ResourceProvider): String {
        var from = ""
        var to = ""

        salaryDto?.from?.let {
            from = "от $it"
        }

        salaryDto?.to?.let {
            to = "до $it"
        }

        return "$from $to ${checkCurrencyIcon(salaryDto?.currency, resourceProvider)}"
    }

    fun checkCurrencyIcon(currency: String?, resourceProvider: ResourceProvider): String {
        return when (currency) {
            "RUR" -> {
                resourceProvider.getString(R.string.RUR)
            }

            "USD" -> {
                resourceProvider.getString(R.string.USD)
            }

            "AZN" -> {
                resourceProvider.getString(R.string.AZN)
            }

            "BYR" -> {
                resourceProvider.getString(R.string.BYR)
            }

            "EUR" -> {
                resourceProvider.getString(R.string.EUR)
            }

            "GEL" -> {
                resourceProvider.getString(R.string.GEL)
            }

            "KGS" -> {
                resourceProvider.getString(R.string.KGS)
            }

            "KZT" -> {
                resourceProvider.getString(R.string.KZT)
            }

            "UAH" -> {
                resourceProvider.getString(R.string.UAH)
            }

            else -> {
                return ""
            }
        }
    }

    fun keySkillsToString(keySkills: List<Skills?>): String {
        var formattedSkills = ""
        if (keySkills.isNotEmpty()) {
            for (i in keySkills.indices) {
                formattedSkills += ("• " + keySkills[i]?.name + "\n")
            }
        }
        return formattedSkills
    }

    fun fromHtml(html: String?): Spanned? {
        return if (html == null) {
            SpannableString("")
        } else {
            Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
        }
    }
}