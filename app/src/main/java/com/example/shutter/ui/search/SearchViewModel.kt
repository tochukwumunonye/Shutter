package com.example.shutter.ui.search

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.shutter.domain.ShutterRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: ShutterRepository,
): ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val photos = currentQuery.switchMap { queryString ->
        repository.searchShutter(queryString).cachedIn(viewModelScope)
    }

    fun searchImages(query: String) {

        viewModelScope.launch {
            repository.searchShutter(query)
        }
        currentQuery.value = query
    }

    companion object {
        private const val DEFAULT_QUERY = "cats"
    }
}
