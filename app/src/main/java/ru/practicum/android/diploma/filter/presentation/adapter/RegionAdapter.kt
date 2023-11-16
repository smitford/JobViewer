package ru.practicum.android.diploma.filter.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.filter.presentation.adapter.model.AreaDataInterface

class RegionAdapter(private val clickListener: ClickListener) :
    RecyclerView.Adapter<RegionViewHolder>() {

    var areas = listOf<AreaDataInterface>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionViewHolder {
        return RegionViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return areas.size
    }

    override fun onBindViewHolder(holder: RegionViewHolder, position: Int) {
        holder.bind(areas[position])
        holder.itemView.setOnClickListener { clickListener.click(areas[position]) }
    }

    fun interface ClickListener {
        fun click(area: AreaDataInterface)
    }
}