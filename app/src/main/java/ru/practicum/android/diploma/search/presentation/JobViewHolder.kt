package ru.practicum.android.diploma.search.presentation

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.RecyclerVacancyItemBinding
import ru.practicum.android.diploma.search.domain.models.Vacancy
import ru.practicum.android.diploma.util.ImgFunctions

class JobViewHolder(private val binding: RecyclerVacancyItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(job: Vacancy) {
        binding.department.text = job.employer
        binding.salary.text = job.salary
        binding.tvJobName.text = job.name

        Glide.with(binding.ivCompany)
            .load(job.employerImgUrl)
            .placeholder(R.drawable.placeholder_32px)
            .centerCrop()
            .transform(
                RoundedCorners(
                    ImgFunctions.roundCorner(
                        itemView.resources
                            .displayMetrics.densityDpi, ROUNDING_OF_CORNERS_PX
                    )
                )
            )
            .into(binding.ivCompany)

    }

    companion object {
        private const val ROUNDING_OF_CORNERS_PX = 12
    }
}