package ru.practicum.android.diploma.filter.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.filter.domain.models.Country

class RegionAdapter(private val clickListener: ClickListener) :
    RecyclerView.Adapter<RegionViewHolder>() {

    var countries = listOf<Country>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionViewHolder {
        return RegionViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: RegionViewHolder, position: Int) {
        holder.bind(countries[position])
        holder.itemView.setOnClickListener { clickListener.click(countries[position]) }
    }

    fun interface ClickListener {
        fun click(track: Country)
    }
}