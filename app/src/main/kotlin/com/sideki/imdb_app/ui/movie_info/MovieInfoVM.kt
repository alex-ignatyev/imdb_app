package com.sideki.imdb_app.ui.movie_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.domain.use_case.GetMovieInfoUseCase
import com.sideki.imdb_app.model.model.MovieInfoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MovieInfoVM @Inject constructor(
    private var getMovieInfoUseCase: GetMovieInfoUseCase
) : ViewModel() {

    var movieInfo = MutableStateFlow((MovieInfoModel()))

    fun getMovieInfo(movieId: String) {
        viewModelScope.launch {
            movieInfo.value = getMovieInfoUseCase.getMovieInfo(movieId)
        }
    }
}
