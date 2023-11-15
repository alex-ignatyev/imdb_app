package com.example.imdb_app.domain.use_case

import com.example.imdb_app.domain.model.MovieInfoModel
import com.example.imdb_app.domain.repository.MovieInfoRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMovieInfoUseCase @Inject constructor(
    private val repo: MovieInfoRepository
) {

    suspend fun getMovieInfo(movieId: String): MovieInfoModel {
        return withContext(Dispatchers.IO) {
            repo.getMovieInfo(movieId)
        }
    }
}
