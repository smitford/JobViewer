package ru.practicum.android.diploma.filter.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilterSettingsBinding
import ru.practicum.android.diploma.filter.presentation.view_model.FilterSettingsViewModel


class FilterSettingsFragment : Fragment() {

    private var _binding : FragmentFilterSettingsBinding? = null
    private val binding get() = _binding!!
    private val vM: FilterSettingsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilterSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ibArrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.tvIndustry.setOnClickListener {
            findNavController().navigate(R.id.action_filterSettingsFragment_to_choosingIndustryFragment2)
        }
        binding.tvPlaceOfWork.setOnClickListener {
            findNavController().navigate(R.id.action_filterSettingsFragment_to_placesOfWorkFragment2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = FilterSettingsFragment()
    }
}