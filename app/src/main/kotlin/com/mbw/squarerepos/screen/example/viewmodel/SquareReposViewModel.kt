package com.mbw.squarerepos.screen.example.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mbw.squarerepos.data.model.square.SquareReposEntity
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mbw.squarerepos.base.viewmodel.BaseViewModel
import com.mbw.squarerepos.data.repository.SquareReposRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SquareReposViewModel @Inject constructor(
    private val repository: SquareReposRepository
) : BaseViewModel() {

    private val _squareReposData =
        MutableStateFlow<PagingData<SquareReposEntity>>(PagingData.empty())
    val squareReposData: StateFlow<PagingData<SquareReposEntity>> = _squareReposData
    var selectedRepo = MutableLiveData<SquareReposEntity>()


    init {
        fetchSquareRepos()
    }

    @OptIn(ExperimentalPagingApi::class)
    private fun fetchSquareRepos() {
        viewModelScope.launch {
            repository.getRepos().cachedIn(viewModelScope).collect { pagingSquerReposData ->
                _squareReposData.value = pagingSquerReposData
            }
        }
    }

    fun updateRepo(data: SquareReposEntity) {
        viewModelScope.launch {
            repository.updateRepo(data)
        }
    }

}
