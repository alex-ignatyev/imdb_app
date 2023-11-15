package com.example.imdb_app.domain.use_case

import com.example.imdb_app.domain.model.ActorInfoModel
import com.example.imdb_app.domain.repository.MovieInfoRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetActorInfoUseCase @Inject constructor(
    private val repo: MovieInfoRepository
) {

    suspend fun getActorInfo(actorId: String): ActorInfoModel {
        return withContext(Dispatchers.IO) {
            repo.getActorInfo(actorId)
        }
    }
}
