package com.sideki.imdb_app.domain.use_case

import com.sideki.imdb_app.domain.MoviesRepository
import com.sideki.imdb_app.domain.model.MovieDataModel
import com.sideki.imdb_app.domain.model.MoviesGroupTitleModel
import com.sideki.imdb_app.domain.model.toModel
import com.sideki.imdb_app.util.recycler.AdapterItem
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository
) {

    suspend fun getMovies(): List<AdapterItem> {
        val topMoviesFromRepo = repository.getMovies()
        val list = mutableListOf<AdapterItem>()
        if (topMoviesFromRepo.isNotEmpty()) {
            list.add(MoviesGroupTitleModel(titleName = "Most popular movies"))
            list.add(MovieDataModel(movies = topMoviesFromRepo.toModel()))
        }
        return list
    }
}
