package ru.practicum.android.diploma.job.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentJobBinding
import ru.practicum.android.diploma.job.domain.models.JobForScreen
import ru.practicum.android.diploma.job.presentation.states.JobScreenState
import ru.practicum.android.diploma.job.presentation.viewmodel.JobFragmentViewModel
import ru.practicum.android.diploma.util.ImgFunctions
import ru.practicum.android.diploma.util.TextUtils

class JobFragment : Fragment() {

    private val jobFragmentViewModel: JobFragmentViewModel by viewModel()
    private lateinit var binding: FragmentJobBinding
    private val args: JobFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        jobFragmentViewModel.getJob(args.jobId)

        jobFragmentViewModel.observeJobScreenLiveData()
            .observe(viewLifecycleOwner) { status ->
                showContentBasedOnState(status)
            }
    }

    private fun showContentBasedOnState(status: JobScreenState) {
        when (status) {
            is JobScreenState.Success -> {
                fillContent(status.jobForScreen)
            }

            is JobScreenState.Loading -> {

            }

            is JobScreenState.ServerError -> {

            }

            is JobScreenState.ConnectionError -> {

            }

            is JobScreenState.InvalidRequest -> {

            }

        }
    }

    private fun fillContent(job: JobForScreen) {
        with(binding) {
            tvJobName.text = job.name
            tvSalary.text = job.salaryFrom
            Glide.with(binding.ivJob)
                .load(job.employerLogoUrl)
                .placeholder(R.drawable.placeholder_32px)
                .centerCrop()
                .transform(
                    RoundedCorners(
                        ImgFunctions.roundCorner(
                            requireActivity().resources
                                .displayMetrics.densityDpi, ROUNDING_OF_CORNERS_PX
                        )
                    )
                )
                .into(binding.ivJob)
            tvEmployer.text = job.employerName
            tvEmployerCity.text = job.area
            tvRequiredExperience.text = job.experience
            tvEmployment.text = job.employment
            tvJobDiscription.text = TextUtils.fromHtml(job.description)
            tvMainSkills.text = TextUtils.keySkillsToString(job.keySkills)
            tvEmailContacts.text = job.email
        }
    }

    companion object {
        private const val ROUNDING_OF_CORNERS_PX = 12
    }
}