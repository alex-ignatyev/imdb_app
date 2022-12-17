package com.sideki.imdb_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.data.api.ImdbApi
import com.sideki.imdb_app.domain.model.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private var api: ImdbApi
) : ViewModel() {

    var data by mutableStateOf("")

    init {
        viewModelScope.launch {
            val response = api.getMovieInfo()
            data = response.toDomain().title
        }
    }
}
