package ru.practicum.android.diploma.filter.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.filter.presentation.adapter.model.AreaDataInterface

class IndustryViewHolder(parentView: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parentView.context).inflate(
        R.layout.recycler_industry_item, parentView, false
    )
) {
    private val name: TextView = itemView.findViewById(R.id.tv_industry_name)
    val rbSelect: RadioButton = itemView.findViewById(R.id.cb_select)

    fun bind(model: AreaDataInterface) {
        when (model) {
            is AreaDataInterface.IndustryUi -> {
                name.text = model.name
                rbSelect.isChecked = model.isSelected
            }

            else -> {}
        }
    }
}