package com.sideki.imdb_app.domain.use_case

import com.sideki.imdb_app.domain.MovieInfoRepository
import com.sideki.imdb_app.model.model.ActorInfoModel
import com.sideki.imdb_app.model.model.toModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetActorInfoUseCase @Inject constructor(
    private val repo: MovieInfoRepository
) {

    suspend fun getActorInfo(actorId: String): ActorInfoModel {
        return withContext(Dispatchers.IO) {
            repo.getActorInfo(actorId).toModel()
        }
    }
}
