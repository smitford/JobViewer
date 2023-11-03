package ru.practicum.android.diploma.search.presentation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.search.domain.models.Filter
import ru.practicum.android.diploma.search.domain.models.Job

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private var filter: Filter = Filter(
        page = 0,
        request = "android",
        area = " ",
        industry = "",
        salary = 1000,
        onlyWithSalary = false
    )
    private val viewModel: SearchViewModel by viewModel { parametersOf(filter) }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.rvSearch
        val adapter = JobAdapter({ _ -> Unit })
        adapter.jobsList = mutableListOf(
            Job(
                id = "1234", area = "Moscow", department = "Eda", employerImgUrl = "",
                employer = "", name = "Android Developer", salary = "from 1 to 100", type = "rab"
            ),
            Job(
                id = "1235", area = "Kiev", department = "Voda", employerImgUrl = "",
                employer = "", name = "Android Developer", salary = "from 1 to 100", type = "rab"
            )
        )
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.loadJobs()

        binding.ivFilter.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_filterSettingsFragment)
        }

    }

}
