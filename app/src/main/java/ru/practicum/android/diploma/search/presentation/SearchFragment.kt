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
import androidx.core.view.isGone
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.search.presentation.models.SearchStates
import ru.practicum.android.diploma.util.TextUtils

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<SearchViewModel>()
    private lateinit var jobClickCb: (String) -> Unit
    private lateinit var adapter: JobAdapter

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshFilter()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        jobClickCb = initClickCb()
        val recyclerView = binding.rvSearch
        adapter = JobAdapter(jobClickCb)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        viewModel.getState().observe(viewLifecycleOwner) { state ->
            when (state) {
                is SearchStates.Start -> setDefaultScreen(state.filterStates)
                is SearchStates.ServerError -> setErrorScreen(state.filterStates)
                is SearchStates.ConnectionError -> setConnectionLostScreen(state.filterStates)
                is SearchStates.InvalidRequest -> setInvalidRequestScreen(state.filterStates)
                is SearchStates.Success -> {
                    setSuccessScreen(state.found, state.filterStates)
                    adapter.jobsList = state.jobList.toMutableList()
                }

                is SearchStates.Loading -> setLoadingPaggScreen(state.isPageRefresher)
                else -> setLoadingPaggScreen(false)
            }
        }

        binding.etSearch.addTextChangedListener(tWCreator())

        binding.ivFilter.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_filterSettingsFragment)
        }

        binding.bClearText.setOnClickListener {
            clearText()
        }

        binding.rvSearch.addOnScrollListener(initScrlLsnr())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setDefaultScreen(hasFilter: Boolean) {
        binding.rvSearch.visibility = GONE
        binding.ivError.setImageResource(R.drawable.search_start)
        binding.tvError.visibility = GONE
        binding.tvRvHeader.visibility = GONE
        binding.pagingPrBar.visibility = GONE
        changeFilterTint(hasFilter)
    }

    private fun setErrorScreen(hasFilter: Boolean) {
        binding.rvSearch.visibility = GONE
        binding.ivError.visibility = VISIBLE
        binding.ivError.setImageResource(R.drawable.error_server_2)
        binding.tvError.visibility = VISIBLE
        binding.tvError.setText(R.string.server_error)
        binding.tvRvHeader.visibility = GONE
        binding.pagingPrBar.visibility = GONE
        changeFilterTint(hasFilter)
    }

    private fun setConnectionLostScreen(hasFilter: Boolean) {
        binding.rvSearch.visibility = GONE
        binding.ivError.visibility = VISIBLE
        binding.ivError.setImageResource(R.drawable.disconnect)
        binding.tvError.visibility = VISIBLE
        binding.tvError.setText(R.string.internet_connection_issue)
        binding.tvRvHeader.visibility = GONE
        binding.pagingPrBar.visibility = GONE
        changeFilterTint(hasFilter)
    }

    private fun setSuccessScreen(amount: Int, hasFilter: Boolean) {
        binding.rvSearch.visibility = VISIBLE
        binding.ivError.visibility = GONE
        binding.tvError.visibility = GONE
        binding.tvRvHeader.visibility = VISIBLE
        binding.tvRvHeader.text = getString(R.string.founded, TextUtils.addSeparator(amount))
        binding.pagingPrBar.visibility = GONE
        changeFilterTint(hasFilter)
    }

    private fun setInvalidRequestScreen(hasFilter: Boolean) {
        binding.rvSearch.visibility = GONE
        binding.ivError.visibility = VISIBLE
        binding.ivError.setImageResource(R.drawable.error_list_favorite)
        binding.tvError.visibility = VISIBLE
        binding.tvError.setText(R.string.error_list_favorite)
        binding.tvRvHeader.visibility = VISIBLE
        binding.tvRvHeader.setText(R.string.vacancy_mismatch)
        binding.pagingPrBar.visibility = GONE
        changeFilterTint(hasFilter)
    }

    private fun setLoadingPaggScreen(pageRefresher: Boolean) {
        binding.rvSearch.isGone = !pageRefresher
        binding.ivError.visibility = GONE
        binding.tvError.visibility = GONE
        binding.pagingPrBar.visibility = VISIBLE
    }

    private fun changeFilterTint(hasFilter: Boolean) {
        if (hasFilter)
            binding.ivFilter.imageTintList =
                ContextCompat.getColorStateList(requireContext(), R.color.filter_tint)
        else
            binding.ivFilter.imageTintList =
                ContextCompat.getColorStateList(requireContext(), R.color.filter_tint_base)
    }

    private fun tWCreator() = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            changeVisBottomNav(GONE)
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (count > 0) {
                val endDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.cross)
                binding.etSearch.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,
                    null,
                    endDrawable,
                    null
                )
            }

            if (start != before) {
                viewModel.loadJobs(s?.toString() ?: "")
            }
        }

        override fun afterTextChanged(s: Editable?) {
            changeVisBottomNav(VISIBLE)
        }
    }

    private fun initClickCb(): (String) -> Unit = { jobId ->
        val arg = SearchFragmentDirections.actionSearchFragmentToJobFragment(jobId)
        findNavController().navigate(arg)
    }

    private fun initScrlLsnr() = object : OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val pos =
                (binding.rvSearch.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            val itemsCount = adapter.itemCount
            if (pos >= itemsCount - 1) {
                viewModel.getNewPage()
            }
        }
    }

    private fun changeVisBottomNav(state: Int) {
        val bottomNav =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.visibility = state
    }

    private fun clearText() {
        binding.etSearch.setText("")
        viewModel.clearAll()
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
    }
}
