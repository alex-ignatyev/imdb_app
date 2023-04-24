package com.sideki.imdb_app.domain.use_case

import com.sideki.imdb_app.db.entity.SelectedMoviesEntity
import javax.inject.Inject

class GetSelectedMovieUseCase @Inject constructor(
    private val repo: SelectedMoviesRepository
) {

    suspend fun getSelectedMovie(id: String): SelectedMoviesEntity {
        return repo.getSelectedMovie(id)
    }
}
