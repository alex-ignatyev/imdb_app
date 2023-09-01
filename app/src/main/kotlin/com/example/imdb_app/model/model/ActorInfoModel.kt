package com.example.imdb_app.model.model

import com.example.imdb_app.model.response.ActorResponse

data class ActorInfoModel(
    val id: String = "Описание отсутствует",
    val name: String = "Описание отсутствует",
    val image: String = "Описание отсутствует",
    val birthDate: String = "Описание отсутствует",
    val deathDate: String = "Описание отсутствует",

    val awards: String = "Описание отсутствует",

    val height: String = "Описание отсутствует",

    val summary: String = "Описание отсутствует",

    val role: String = "Описание отсутствует",

    val knownFor: List<KnownForModel> = listOf(),
    val castMovies: List<CastMovieModel> = listOf()
) {

    data class KnownForModel(
        val id: String,
        val title: String,
        val fullTitle: String,
        val image: String,
        val year: String,
        val role: String,
    )

    data class CastMovieModel(
        val id: String,
        val title: String,
        val description: String,
        val year: String,
        val role: String
    )
}

fun ActorResponse.toModel() = ActorInfoModel(
    id = id.orEmpty(),
    name = name.orEmpty(),
    image = image.orEmpty(),
    birthDate = birthDate.orEmpty(),
    deathDate = deathDate.orEmpty(),

    height = height.orEmpty(),

    role = role.orEmpty(),

    summary = summary.orEmpty(),

    awards = awards.orEmpty(),

    knownFor = knownFor?.map {
        ActorInfoModel.KnownForModel(
            id = it.id.orEmpty(),
            title = it.title.orEmpty(),
            fullTitle = it.fullTitle.orEmpty(),
            image = it.image.orEmpty(),
            year = it.year.orEmpty(),
            role = it.role.orEmpty()
        )
    }.orEmpty(),

    castMovies = castMovies?.map {
        ActorInfoModel.CastMovieModel(
            id = it.id.orEmpty(),
            title = it.title.orEmpty(),
            description = it.description.orEmpty(),
            year = it.year.orEmpty(),
            role = it.role.orEmpty()
        )
    }.orEmpty()
)
