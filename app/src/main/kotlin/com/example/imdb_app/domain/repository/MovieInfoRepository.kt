package com.example.imdb_app.domain.repository

import com.example.imdb_app.domain.model.ActorInfoModel
import com.example.imdb_app.domain.model.MovieInfoModel

interface MovieInfoRepository {

    suspend fun getMovieInfo(movieId: String): MovieInfoModel

    suspend fun getActorInfo(actorId: String): ActorInfoModel
}
