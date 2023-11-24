package ru.practicum.android.diploma.util

import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.job.data.impl.mapper.TypeForTextUtils
import ru.practicum.android.diploma.job.data.secondarymodels.Address
import ru.practicum.android.diploma.job.data.secondarymodels.Phones
import ru.practicum.android.diploma.job.data.secondarymodels.Skills


object TextUtils {
    fun checkSalaryBorder(salaryDto: Salary?, resourceProvider: ResourceProvider): String {
        var from = ""
        var to = ""

        salaryDto?.from?.let {
            from = "${resourceProvider.getString(R.string.from)} ${addSeparator(it)}"
        }

        salaryDto?.to?.let {
            to = "${resourceProvider.getString(R.string.to)} ${addSeparator(it)}"
        }

        return "$from $to ${checkCurrencyIcon(salaryDto?.currency, resourceProvider)}"
    }

    fun getSalaryString(salaryDto: Salary?, resourceProvider: ResourceProvider): String =
        if (salaryDto == null)
            resourceProvider.getString(R.string.empty_salary)
        else
            checkSalaryBorder(salaryDto, resourceProvider)

    fun checkCurrencyIcon(currency: String?, resourceProvider: ResourceProvider): String {
        return when (currency) {
            "RUR" -> resourceProvider.getString(R.string.RUR)
            "USD" -> resourceProvider.getString(R.string.USD)
            "AZN" -> resourceProvider.getString(R.string.AZN)
            "BYR" -> resourceProvider.getString(R.string.BYR)
            "EUR" -> resourceProvider.getString(R.string.EUR)
            "GEL" -> resourceProvider.getString(R.string.GEL)
            "KGS" -> resourceProvider.getString(R.string.KGS)
            "KZT" -> resourceProvider.getString(R.string.KZT)
            "UAH" -> resourceProvider.getString(R.string.UAH)
            "UZS" -> resourceProvider.getString(R.string.UZS)
            else -> return ""
        }
    }

    fun arrayToStrInJob(array: Array<Any>?, itemType: TypeForTextUtils): String {
        var formattedString = ""
        if (!array.isNullOrEmpty()) {
            for (i in array.indices) {
                val item: Any
                when (itemType) {
                    is TypeForTextUtils.Skills ->
                        if (array.isArrayOf<Skills>()) {
                            item = array[i] as Skills
                            item.name?.let { formattedString += ("• " + item.name + "\n") }
                        }

                    is TypeForTextUtils.Phones ->
                        if (array.isArrayOf<Phones>()) {
                            item = array[i] as Phones
                            item.formatted?.let { formattedString += item.formatted + "\n" }
                        }

                    is TypeForTextUtils.Comment ->
                        if (array.isArrayOf<Phones>()) {
                            item = array[i] as Phones
                            item.comment?.let { formattedString += (item.comment + "\n") }
                        }
                }
            }
        }
        return formattedString
    }

    fun employerAddressToString(address: Address?, area: String?): String {
        val addressList = mutableListOf<String>()
        var resultStr = ""

        if (address != null) {
            address.city?.let {
                addressList.add(it)
            }
            address.street?.let {
                addressList.add(it)
            }
            address.building?.let {
                addressList.add(it)
            }
            resultStr = addressList.joinToString(", ")
        } else {
            area?.let { resultStr = area }
        }
        return resultStr
    }

    fun fromHtml(html: String?): Spanned? {
        return if (html == null) {
            SpannableString("")
        } else {
            Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
        }
    }

    fun addSeparator(number: Int) = "%,d".format(number).replace(",", " ")
}