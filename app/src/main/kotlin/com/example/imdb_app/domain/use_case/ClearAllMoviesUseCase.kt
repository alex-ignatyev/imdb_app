package com.example.imdb_app.domain.use_case

import com.example.imdb_app.domain.MoviesRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ClearAllMoviesUseCase @Inject constructor(
    private val repo: MoviesRepository
) {

    suspend fun clearAllMoviesIfNeeded() {
        withContext(Dispatchers.IO) {
            repo.clearAllMovies()
        }
    }
}
