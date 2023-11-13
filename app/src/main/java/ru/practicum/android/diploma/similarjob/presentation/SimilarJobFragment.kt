package ru.practicum.android.diploma.similarjob.presentation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSimilarJobBinding
import ru.practicum.android.diploma.search.domain.api.DtoConsumer
import ru.practicum.android.diploma.search.domain.models.Job
import ru.practicum.android.diploma.search.domain.models.JobsInfo
import ru.practicum.android.diploma.search.presentation.JobAdapter

class SimilarJobFragment : Fragment() {


    companion object {
        private const val IDJOB = "id_job"

        fun createArgs(id: String?): Bundle {
            return bundleOf(IDJOB to id)
        }
    }

    private lateinit var binding: FragmentSimilarJobBinding
    private lateinit var jobClickCb: (String) -> Unit

    private val viewModel: SimilarJobViewModel by viewModel{
        parametersOf(requireArguments().getString(IDJOB))
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSimilarJobBinding.inflate(inflater,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jobClickCb = initClickCb()
        val recyclerView = binding.rvSearch
        val adapter = JobAdapter(jobClickCb)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.getSimilarLiveData().observe(viewLifecycleOwner){

            when(it){
                is DtoConsumer.Success<JobsInfo> -> {
                    binding.rvSearch.isGone = false
                    binding.tvMessage.isGone = true
                    binding.ivPlaceholderPng.isGone = true
                    // Заполнение списка вакансии
                    adapter.jobsList = it.data.jobs as MutableList<Job>
                    adapter.notifyDataSetChanged()

                }

                is DtoConsumer.NoInternet<JobsInfo> -> {
                    // No internet
                    binding.rvSearch.isGone = true
                    binding.tvMessage.isGone = false
                    binding.ivPlaceholderPng.isGone = false
                    binding.ivPlaceholderPng.setImageResource(R.drawable.disconnect)
                    binding.tvMessage.text = getText(R.string.no_internet)
                }

                else ->{
                    // error
                    binding.rvSearch.isGone = true
                    binding.tvMessage.isGone = false
                    binding.ivPlaceholderPng.isGone = false
                    binding.ivPlaceholderPng.setImageResource(R.drawable.error_server_2)
                    binding.tvMessage.text = getText(R.string.server_error)
                }
            }
        }

        viewModel.getSimilar()

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun initClickCb(): (String) -> Unit = { jobId ->
        val arg = SimilarJobFragmentDirections.actionSimilarJobFragmentToJobFragment(jobId)
        findNavController().navigate(arg)
    }
}