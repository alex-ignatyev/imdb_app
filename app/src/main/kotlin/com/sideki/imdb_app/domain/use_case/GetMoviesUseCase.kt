package com.sideki.imdb_app.domain.use_case

import com.sideki.imdb_app.domain.MoviesRepository
import com.sideki.imdb_app.domain.model.MovieDataModel
import com.sideki.imdb_app.domain.model.MoviesGroupTitleModel
import com.sideki.imdb_app.domain.model.toModel
import com.sideki.imdb_app.util.recycler.AdapterItem
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository
) {

    suspend fun getMovies(): List<AdapterItem> {
        return withContext(Dispatchers.IO) {
            val mostPopularMovies = repository.getMostPopularMovies()
            val top250Movies = repository.getTop250Movies()
            val top250TVs = repository.getTop250TVs()
            val comingSoonMovies = repository.getComingSoonMovies()
            val list = mutableListOf<AdapterItem>()
            if (mostPopularMovies.isNotEmpty()) {
                list.add(MoviesGroupTitleModel(titleName = "Most popular movies"))
                list.add(MovieDataModel(movies = mostPopularMovies.toModel()))
            }
            if (top250Movies.isNotEmpty()) {
                list.add(MoviesGroupTitleModel(titleName = "Top 250 movies"))
                list.add(MovieDataModel(movies = top250Movies.toModel()))
            }
            if (top250TVs.isNotEmpty()) {
                list.add(MoviesGroupTitleModel(titleName = "Top 250 TVs"))
                list.add(MovieDataModel(movies = top250TVs.toModel()))
            }
            if (comingSoonMovies.isNotEmpty()) {
                list.add(MoviesGroupTitleModel(titleName = "Coming soon"))
                list.add(MovieDataModel(movies = comingSoonMovies.toModel()))
            }
            list
        }
    }
}
