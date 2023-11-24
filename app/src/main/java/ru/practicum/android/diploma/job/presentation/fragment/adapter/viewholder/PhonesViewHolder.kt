package ru.practicum.android.diploma.job.presentation.fragment.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R

class PhonesViewHolder(phone: View) : RecyclerView.ViewHolder(phone) {
    private val phoneTv: TextView = phone.findViewById(R.id.phone_item)

    fun bind(formatted: String, trackListClickListener: PhoneClickListener) {
        phoneTv.text = formatted
        itemView.setOnClickListener {
            trackListClickListener.setPhoneClickListener(formatted)
        }
    }

    interface PhoneClickListener {
        fun setPhoneClickListener(phone: String)
    }
}