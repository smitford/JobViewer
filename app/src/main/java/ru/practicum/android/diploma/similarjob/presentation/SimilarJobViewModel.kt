package ru.practicum.android.diploma.similarjob.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.search.domain.api.DtoConsumer
import ru.practicum.android.diploma.search.domain.models.JobsInfo
import ru.practicum.android.diploma.similarjob.presentation.api.SimilarJobInteractor

class SimilarJobViewModel(private val similarJobInteractor: SimilarJobInteractor,private val id:String): ViewModel() {

    private val similar = MutableLiveData<DtoConsumer<JobsInfo>>()

    fun getSimilarLiveData(): LiveData<DtoConsumer<JobsInfo>> = similar

    fun getSimilar(){
        viewModelScope.launch {
            similarJobInteractor.getSimilarJobs(id).collect{
                similar.value = it
            }
        }
    }
}