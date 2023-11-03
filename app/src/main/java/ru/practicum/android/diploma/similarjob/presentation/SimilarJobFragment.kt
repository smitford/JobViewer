package ru.practicum.android.diploma.similarjob.presentation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isGone
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSimilarJobBinding
import ru.practicum.android.diploma.favorite.presentation.FavoriteViewModel
import ru.practicum.android.diploma.similarjob.SimilarJobState

class SimilarJobFragment : Fragment() {

    private lateinit var binding: FragmentSimilarJobBinding
    private val viewModel: SimilarJobViewModel by viewModel()

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

        viewModel.getSimilarLiveData().observe(viewLifecycleOwner){

            when(it.first){
                SimilarJobState.DATA -> {
                    binding.rvSearch.isGone = false
                    binding.tvMessage.isGone = true
                    binding.ivPlaceholderPng.isGone = true
                    // Заполнение списка вакансии
                }

                SimilarJobState.NOINTERNET -> {
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

        viewModel.getSimilar(0L)

    }
}