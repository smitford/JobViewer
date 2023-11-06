package ru.practicum.android.diploma.search.presentation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.search.domain.models.Filter
import ru.practicum.android.diploma.search.presentation.models.SearchStates

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private var filter: Filter = Filter(
        page = 0,
        request = "android",
        area = null,
        industry = null,
        salary = null,
        onlyWithSalary = false
    )

    private val viewModel: SearchViewModel by viewModel { parametersOf(filter) }
    private lateinit var jobClickCb: (String) -> Unit
    private var searchJob: Job? = null

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
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

        viewModel.getState().observe(viewLifecycleOwner) { state ->
            when (state) {
                is SearchStates.Default -> setDefaultScreen()
                is SearchStates.ServerError -> setErrorScreen()
                is SearchStates.ConnectionError -> setConnectionLostScreen()
                is SearchStates.InvalidRequest -> setInvalidRequestScreen()
                is SearchStates.Success -> {
                    setSuccessScreen(state.trackList.count()) //Передать общее кол-во найденных вакансий
                    adapter.jobsList = state.trackList.toMutableList()
                    adapter.notifyDataSetChanged()
                }

                is SearchStates.Loading -> setLoadingScreen()
            }
        }


        binding.etSearch.addTextChangedListener(tWCreator())



        binding.ivFilter.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_filterSettingsFragment)
        }

        binding.bClearText.setOnClickListener {
            clearText()
        }

    }

    private fun setDefaultScreen() {
        binding.rvSearch.visibility = GONE
        binding.ivError.setImageResource(R.drawable.search_start)
        binding.searchProgressBar.visibility = GONE
        binding.tvError.visibility = GONE
        binding.tvRvHeader.visibility = GONE
    }

    private fun setErrorScreen() {
        binding.rvSearch.visibility = GONE
        binding.ivError.setImageResource(R.drawable.error_server_2)
        binding.searchProgressBar.visibility = GONE
        binding.tvError.visibility = VISIBLE
        binding.tvError.setText(R.string.server_error)
        binding.tvRvHeader.visibility = GONE
    }

    private fun setConnectionLostScreen() {
        binding.rvSearch.visibility = GONE
        binding.ivError.visibility = VISIBLE
        binding.ivError.setImageResource(R.drawable.disconnect)
        binding.searchProgressBar.visibility = GONE
        binding.tvError.visibility = VISIBLE
        binding.tvError.setText(R.string.internet_connection_issue)
        binding.tvRvHeader.visibility = GONE
    }

    private fun setSuccessScreen(amount: Int) {
        binding.rvSearch.visibility = VISIBLE
        binding.ivError.visibility = GONE
        binding.searchProgressBar.visibility = GONE
        binding.tvError.visibility = GONE
        binding.tvRvHeader.visibility = VISIBLE
        binding.tvRvHeader.text = amount.toString()
    }

    private fun setInvalidRequestScreen() {
        binding.rvSearch.visibility = GONE
        binding.ivError.setImageResource(R.drawable.error_list_favorite)
        binding.searchProgressBar.visibility = GONE
        binding.tvError.visibility = VISIBLE
        binding.tvError.setText(R.string.internet_connection_issue)
        binding.tvRvHeader.visibility = VISIBLE
        binding.tvRvHeader.setText(R.string.vacancy_mismatch)
    }

    private fun setLoadingScreen() {
        binding.rvSearch.visibility = GONE
        binding.ivError.visibility = GONE
        binding.searchProgressBar.visibility = VISIBLE
        binding.tvError.visibility = GONE
        binding.tvRvHeader.visibility = GONE
    }

    private fun tWCreator() = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            changeVisBottomNav(GONE)
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val endDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.cross)
            binding.etSearch.setCompoundDrawablesRelativeWithIntrinsicBounds(
                null,
                null,
                endDrawable,
                null
            )

            if (start != before) {
                searchJob?.cancel()
                searchJob = viewLifecycleOwner.lifecycleScope.launch {
                    delay(SEARCH_DEBOUNCE_DELAY_MILS)
                    filter.request = s?.toString() ?: ""
                    viewModel.loadJobs()

                }
            }
        }

        override fun afterTextChanged(s: Editable?) {
            changeVisBottomNav(VISIBLE)
        }

    }

    private fun initClickCb() : (String) -> Unit = { jobId->
        val arg =SearchFragmentDirections.actionSearchFragmentToJobFragment(jobId)
        findNavController().navigate(arg)
    }
    private fun changeVisBottomNav(state: Int) {
        val bottomNav =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.visibility = state
    }

    private fun clearText() {
        binding.etSearch.setText("")
        val endDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.search)
        binding.etSearch.setCompoundDrawablesRelativeWithIntrinsicBounds(
            null,
            null,
            endDrawable,
            null
        )
    }


    companion object {
        const val VISIBLE = View.VISIBLE
        const val GONE = View.GONE
        const val SEARCH_DEBOUNCE_DELAY_MILS = 2000L
    }
}
