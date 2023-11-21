package ru.practicum.android.diploma.filter.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.filter.presentation.adapter.model.AreaDataInterface

class FilterAdapter(private val clickListener: ClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list = listOf<AreaDataInterface>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_REGION -> RegionViewHolder(parent)
            VIEW_TYPE_INDUSTRY -> IndustryViewHolder(parent)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is RegionViewHolder -> holder.bind(list[position])
            is IndustryViewHolder -> holder.bind(list[position])
        }

        holder.itemView.setOnClickListener { clickListener.click(list[position]) }
    }

    fun interface ClickListener {
        fun click(area: AreaDataInterface)
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is AreaDataInterface.RegionUi -> VIEW_TYPE_REGION
            is AreaDataInterface.IndustryUi -> VIEW_TYPE_INDUSTRY
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    companion object {
        const val VIEW_TYPE_REGION = 1
        const val VIEW_TYPE_INDUSTRY = 2
    }
}