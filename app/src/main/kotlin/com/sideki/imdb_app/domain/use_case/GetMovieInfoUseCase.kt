package com.sideki.imdb_app.domain.use_case

import com.sideki.imdb_app.domain.MovieInfoRepository
import com.sideki.imdb_app.model.model.MovieInfoModel
import com.sideki.imdb_app.model.model.toModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMovieInfoUseCase @Inject constructor(
    private val repo: MovieInfoRepository
) {

    suspend fun getMovieInfo(movieId: String): MovieInfoModel {
        return withContext(Dispatchers.IO) {
            repo.getMovieInfo(movieId).toModel()
        }
    }
}
