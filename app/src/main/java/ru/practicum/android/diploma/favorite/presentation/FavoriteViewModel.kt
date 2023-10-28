package ru.practicum.android.diploma.favorite.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.favorite.domain.FavoriteTrack
import ru.practicum.android.diploma.favorite.domain.StateFavorite
import ru.practicum.android.diploma.favorite.presentation.api.FavoriteInteractor

class FavoriteViewModel(private val favoriteInteractor: FavoriteInteractor): ViewModel() {

    private var favorite = MutableLiveData<Pair<StateFavorite,ArrayList<FavoriteTrack>>>()

    fun getFavoriteLiveData(): LiveData<Pair<StateFavorite,ArrayList<FavoriteTrack>>> = favorite

    fun getFavorite(){
        viewModelScope.launch {
            favoriteInteractor.get().collect{
                favorite.value = it
            }
        }
    }
}