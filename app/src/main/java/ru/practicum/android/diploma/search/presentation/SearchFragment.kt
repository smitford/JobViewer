package ru.practicum.android.diploma.search.presentation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.search.domain.models.JobInfo

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

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
            JobInfo(
                id = "1234", area = "Moscow", department = "Eda", employerImgUrl = "",
                employer = "", name = "Android Developer", salary = "from 1 to 100", type = "rab"
            ),
            JobInfo(
                id = "1235", area = "Kiev", department = "Voda", employerImgUrl = "",
                employer = "", name = "Android Developer", salary = "from 1 to 100", type = "rab"
            )
        )
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter


    }

}
