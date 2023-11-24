package ru.practicum.android.diploma.job.presentation.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.job.presentation.fragment.adapter.viewholder.PhonesViewHolder

class PhonesAdapter(
    private val phonesList: List<String>,
    private val phoneClickListener: PhonesViewHolder.PhoneClickListener
) : RecyclerView.Adapter<PhonesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhonesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_phone_item, parent, false)
        return PhonesViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhonesViewHolder, position: Int) {
        holder.bind(phonesList[position], phoneClickListener)
    }

    override fun getItemCount(): Int {
        return phonesList.size
    }
}