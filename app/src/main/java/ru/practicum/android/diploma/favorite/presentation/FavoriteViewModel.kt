package ru.practicum.android.diploma.favorite.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.favorite.domain.FavoriteState
import ru.practicum.android.diploma.favorite.presentation.api.FavoriteInteractor
import ru.practicum.android.diploma.search.domain.models.Vacancy

class FavoriteViewModel(private val favoriteInteractor: FavoriteInteractor): ViewModel() {

    private val favorite = MutableLiveData<Pair<FavoriteState,ArrayList<Vacancy>>>()

    fun getFavoriteLiveData(): LiveData<Pair<FavoriteState,ArrayList<Vacancy>>> = favorite

    fun getFavorite(){
        viewModelScope.launch {
            favoriteInteractor.get().collect{
                favorite.value = it
            }
        }
    }
}