package com.sideki.imdb_app.domain.use_case

import com.sideki.imdb_app.db.entity.SelectedMoviesEntity
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InsertSelectedMovieUseCase @Inject constructor(
    private val repo: SelectedMoviesRepository
) {

    suspend fun insertSelectedMovie(movie: SelectedMoviesEntity) {
        return withContext(Dispatchers.IO) { repo.insertSelectedMovie(movie) }
    }
}
