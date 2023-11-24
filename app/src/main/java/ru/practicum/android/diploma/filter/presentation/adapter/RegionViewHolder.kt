package ru.practicum.android.diploma.filter.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.filter.presentation.adapter.model.AreaDataInterface

class RegionViewHolder(parentView: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parentView.context).inflate(
        R.layout.recycler_area_item, parentView, false
    )
) {
    private val areaName: TextView = itemView.findViewById(R.id.tv_country_item)

    fun bind(model: AreaDataInterface) {
        areaName.text = model.name
    }
}