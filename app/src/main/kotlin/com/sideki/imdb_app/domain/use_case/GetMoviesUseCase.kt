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
        val mostPopularMoviesFromRepo = repository.getMostPopularMovies()
        val top250MoviesFromRepo = repository.getTop250Movies()
        val top250TVsFromRepo = repository.getTop250TVs()
        val comingSoonMoviesFromRepo = repository.getComingSoonMovies()
        val list = mutableListOf<AdapterItem>()
        if (mostPopularMoviesFromRepo.isNotEmpty()) {
            list.add(MoviesGroupTitleModel(titleName = "Most popular movies"))
            list.add(MovieDataModel(movies = mostPopularMoviesFromRepo.toModel()))
        }
        if (top250MoviesFromRepo.isNotEmpty()) {
            list.add(MoviesGroupTitleModel(titleName = "Top 250 movies"))
            list.add(MovieDataModel(movies = top250MoviesFromRepo.toModel()))
        }
        if (top250TVsFromRepo.isNotEmpty()) {
            list.add(MoviesGroupTitleModel(titleName = "Top 250 TVs"))
            list.add(MovieDataModel(movies = top250TVsFromRepo.toModel()))
        }
        if (comingSoonMoviesFromRepo.isNotEmpty()) {
            list.add(MoviesGroupTitleModel(titleName = "Coming soon"))
            list.add(MovieDataModel(movies = comingSoonMoviesFromRepo.toModel()))
        }
        return list
    }
}
