package com.sideki.imdb_app.domain

import com.sideki.imdb_app.data.api.ImdbApi
import com.sideki.imdb_app.model.response.ActorResponse
import com.sideki.imdb_app.model.response.MovieInfoResponse
import javax.inject.Inject

class MovieInfoRepository @Inject constructor(
    private val imdbApi: ImdbApi
) {

    //FIXME Добавить MovieInfoEntity ?
    suspend fun getMovieInfo(movieId: String): MovieInfoResponse {
        return imdbApi.getMovieInfo(movieId = movieId)
    }

    //FIXME Добавить ActorEntity ?
    suspend fun getActorInfo(actorId: String): ActorResponse {
        return imdbApi.getActorInfo(actorId = actorId)
    }
}
