package ru.practicum.android.diploma.util

import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.job.data.impl.mapper.TypeForMapper
import ru.practicum.android.diploma.job.data.secondarymodels.Phones
import ru.practicum.android.diploma.job.data.secondarymodels.Skills
import ru.practicum.android.diploma.job.data.secondarymodels.Salary


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

    private fun checkCurrencyIcon(currency: String?, resourceProvider: ResourceProvider): String {
        return when (currency) {
            "RUR" -> {resourceProvider.getString(R.string.RUR)}
            "USD" -> {resourceProvider.getString(R.string.USD)}
            "AZN" -> {resourceProvider.getString(R.string.AZN)}
            "BYR" -> {resourceProvider.getString(R.string.BYR)}
            "EUR" -> {resourceProvider.getString(R.string.EUR)}
            "GEL" -> {resourceProvider.getString(R.string.GEL)}
            "KGS" -> {resourceProvider.getString(R.string.KGS)}
            "KZT" -> {resourceProvider.getString(R.string.KZT)}
            "UAH" -> {resourceProvider.getString(R.string.UAH)}
            else -> {
                return ""
            }
        }
    }

    fun arrayToStrInJob(array: Array<Any>?, itemType: TypeForMapper): String {
        var formattedString = ""
        if (!array.isNullOrEmpty()) {
            for (i in array.indices) {
                val item: Any
                when(itemType) {
                   is TypeForMapper.Skills ->
                       if (array.isArrayOf<Skills>()) {
                           item = array[i] as Skills
                           formattedString += ("• " + item.name + "\n")
                       }
                   is TypeForMapper.Phones ->
                       if (array.isArrayOf<Phones>()) {
                           item = array[i] as Phones
                           formattedString += (item.formatted + "\n")
                       }
                    is TypeForMapper.Comment ->
                        if (array.isArrayOf<Phones>()) {
                            item = array[i] as Phones
                            formattedString += (item.comment + "\n")
                        }

                }
            }
        }
        return formattedString
    }

    fun fromHtml(html: String?): Spanned? {
        return if (html == null) {
            SpannableString("")
        } else {
            Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
        }
    }
}