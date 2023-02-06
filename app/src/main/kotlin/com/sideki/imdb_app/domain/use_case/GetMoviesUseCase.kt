package com.sideki.imdb_app.domain.use_case

import com.sideki.imdb_app.domain.MoviesRepository
import com.sideki.imdb_app.model.entity.MovieEntity
import com.sideki.imdb_app.model.entity.MovieEntity.MovieType
import com.sideki.imdb_app.model.entity.MovieEntity.MovieType.COMING_SOON_MOVIES
import com.sideki.imdb_app.model.entity.MovieEntity.MovieType.MOST_POPULAR_MOVIES
import com.sideki.imdb_app.model.entity.MovieEntity.MovieType.TOP_250_MOVIES
import com.sideki.imdb_app.model.entity.MovieEntity.MovieType.TOP_250_TVS
import com.sideki.imdb_app.model.model.MovieDataModel
import com.sideki.imdb_app.model.model.MoviesGroupTitleModel
import com.sideki.imdb_app.model.model.toModel
import com.sideki.imdb_app.util.recycler.AdapterItem
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMoviesUseCase @Inject constructor(
    private val repo: MoviesRepository
) {

    suspend fun getMovies(): List<AdapterItem> {
        return withContext(Dispatchers.IO) {
            mutableListOf<AdapterItem>().apply {
                getMoviesByType(MOST_POPULAR_MOVIES) { repo.getMostPopularMovies() }
                getMoviesByType(TOP_250_MOVIES) { repo.getTop250Movies() }
                getMoviesByType(TOP_250_TVS) { repo.getTop250TVs() }
                getMoviesByType(COMING_SOON_MOVIES) { repo.getComingSoonMovies() }
            }
        }
    }

    private inline fun MutableList<AdapterItem>.getMoviesByType(
        type: MovieType,
        moviesFromRepo: () -> List<MovieEntity>
    ) {
        val movies = moviesFromRepo.invoke()
        if (movies.isNotEmpty()) {
            this.add(MoviesGroupTitleModel(titleName = type.name))
            this.add(MovieDataModel(movies = movies.toModel()))
        }
    }
}
