package ru.practicum.android.diploma.favorite.presentation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFavoriteBinding
import ru.practicum.android.diploma.favorite.domain.StateFavorite


class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModel()

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivPlaceholderPng.setImageResource(R.drawable.error_list_favorite)
        binding.tvMessage.text = getText(R.string.error_list_favorite)

        viewModel.getFavoriteLiveData().observe(viewLifecycleOwner){

            when (it.first) {
                StateFavorite.FULL -> {
                    // есть треки
                    var f = 0
                }
                StateFavorite.EMPTY -> {
                    // Пустой список
                    binding.ivPlaceholderPng.setImageResource(R.drawable.empty_list_favorite)
                    binding.tvMessage.text = getText(R.string.empty_list)
                }
                else -> {
                    // Ошибка
                    binding.ivPlaceholderPng.setImageResource(R.drawable.error_list_favorite)
                    binding.tvMessage.text = getText(R.string.error_list_favorite)
                }
            }
        }

        viewModel.getFavorite()

    }
}