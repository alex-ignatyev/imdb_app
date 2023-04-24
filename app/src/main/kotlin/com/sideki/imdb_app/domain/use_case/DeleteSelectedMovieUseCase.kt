package com.sideki.imdb_app.domain.use_case

import com.sideki.imdb_app.db.entity.SelectedMoviesEntity
import javax.inject.Inject

class DeleteSelectedMovieUseCase @Inject constructor(
    private val repo: SelectedMoviesRepository
) {

    suspend fun deleteSelectedMovie(movie: SelectedMoviesEntity) {
        return repo.deleteSelectedMovie(movie)
    }
}
