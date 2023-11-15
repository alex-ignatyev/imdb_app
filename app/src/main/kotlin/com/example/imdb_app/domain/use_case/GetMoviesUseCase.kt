package com.example.imdb_app.domain.use_case

import com.example.imdb_app.data.db.entity.MovieEntity.MovieType
import com.example.imdb_app.data.db.entity.MovieEntity.MovieType.COMING_SOON_MOVIES
import com.example.imdb_app.data.db.entity.MovieEntity.MovieType.MOST_POPULAR_MOVIES
import com.example.imdb_app.data.db.entity.MovieEntity.MovieType.TOP_250_MOVIES
import com.example.imdb_app.data.db.entity.MovieEntity.MovieType.TOP_250_TVS
import com.example.imdb_app.domain.model.MovieDataModel
import com.example.imdb_app.domain.model.MovieDataModel.MovieModel
import com.example.imdb_app.domain.model.MoviesGroupTitleModel
import com.example.imdb_app.domain.repository.MoviesRepository
import com.example.imdb_app.util.recycler.AdapterItem
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
        moviesFromRepo: () -> List<MovieModel>
    ) {
        val movies = moviesFromRepo.invoke()
        if (movies.isNotEmpty()) {
            this.add(MoviesGroupTitleModel(titleName = type.title))
            this.add(MovieDataModel(movies = movies))
        }
    }
}
