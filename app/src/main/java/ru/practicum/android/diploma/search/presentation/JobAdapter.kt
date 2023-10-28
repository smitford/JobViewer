package ru.practicum.android.diploma.search.presentation


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.RecyclerVacancyItemBinding
import ru.practicum.android.diploma.search.domain.models.JobInfo


class JobAdapter(val onJobClickedCB: (JobInfo) -> Int) : RecyclerView.Adapter<JobViewHolder>() {

    var jobsList = mutableListOf<JobInfo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return JobViewHolder(
            RecyclerVacancyItemBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = jobsList.size

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(jobsList[position])
        holder.itemView.setOnClickListener {
            onJobClickedCB(jobsList[position])
        }
    }
}