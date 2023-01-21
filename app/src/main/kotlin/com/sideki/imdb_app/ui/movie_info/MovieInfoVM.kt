package com.sideki.imdb_app.ui.movie_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.data.api.ImdbApi
import com.sideki.imdb_app.model.model.MovieInfoModel
import com.sideki.imdb_app.model.model.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MovieInfoVM @Inject constructor(
    private var api: ImdbApi
) : ViewModel() {

    var movieInfo = MutableStateFlow((MovieInfoModel()))

    fun getMovieInfo(id: String) {
        viewModelScope.launch {
            val response = api.getMovieInfo(titleId = id)
            movieInfo.value = response.toDomain()
        }
    }
}
