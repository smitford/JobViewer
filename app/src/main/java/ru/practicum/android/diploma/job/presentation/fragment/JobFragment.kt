package ru.practicum.android.diploma.job.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.databinding.FragmentJobBinding
import ru.practicum.android.diploma.job.presentation.viewmodel.JobFragmentViewModel

class JobFragment : Fragment() {

    private lateinit var binding: FragmentJobBinding
    private val jobFragmentViewModel: JobFragmentViewModel by viewModel()
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
    }
}