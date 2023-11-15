package com.example.imdb_app.data.repository

import com.example.imdb_app.data.api.ImdbApi
import com.example.imdb_app.domain.model.ActorInfoModel
import com.example.imdb_app.domain.model.MovieInfoModel
import com.example.imdb_app.domain.model.toModel
import com.example.imdb_app.domain.repository.MovieInfoRepository
import javax.inject.Inject

class MovieInfoRepositoryImpl @Inject constructor(
    private val imdbApi: ImdbApi
) : MovieInfoRepository {

    //FIXME Добавить MovieInfoEntity ?
    override suspend fun getMovieInfo(movieId: String): MovieInfoModel {
        return imdbApi.getMovieInfo(movieId = movieId).toModel()
    }

    //FIXME Добавить ActorEntity ?
    override suspend fun getActorInfo(actorId: String): ActorInfoModel {
        return imdbApi.getActorInfo(actorId = actorId).toModel()
    }
}
