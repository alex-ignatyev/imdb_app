package com.sideki.imdb_app.domain.use_case

import com.sideki.imdb_app.db.entity.SelectedMoviesEntity
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetSelectedMovieUseCase @Inject constructor(
    private val repo: SelectedMoviesRepository
) {

    suspend fun getSelectedMovie(id: String): SelectedMoviesEntity {
        return withContext(Dispatchers.IO) { repo.getSelectedMovie(id) }
    }
}
