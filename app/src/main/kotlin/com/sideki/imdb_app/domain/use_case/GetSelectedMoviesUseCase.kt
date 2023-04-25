package com.sideki.imdb_app.domain.use_case

import com.sideki.imdb_app.db.entity.SelectedMoviesEntity
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetSelectedMoviesUseCase @Inject constructor(
    private val repo: SelectedMoviesRepository
) {

    suspend fun getSelectedMovies(): List<SelectedMoviesEntity> {
        return withContext(Dispatchers.IO) { repo.getSelectedMovies() }
    }
}
