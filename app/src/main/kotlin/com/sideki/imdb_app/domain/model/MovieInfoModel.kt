package com.sideki.imdb_app.domain.model

import com.sideki.imdb_app.data.response.MovieInfoResponse
import com.sideki.imdb_app.domain.model.MovieInfoModel.ActorModel
import com.sideki.imdb_app.domain.model.MovieInfoModel.BoxOfficeModel
import com.sideki.imdb_app.domain.model.MovieInfoModel.SimilarMovieModel

data class MovieInfoModel(
    val movieId: String = "",
    val title: String = "Без названия",
    val description: String = "Описание отсутствует",
    val year: String = "1900",
    val imageUrl: String = "",

    val genres: String = "боевик, комедия",
    val runtimeMins: String = "127",
    val countries: String = "США",

    val rating: String = "0.0",
    val ratingVotes: String = "0", //TODO Парсить количество

    val directors: String = "Информация отстутствует",

    val actors: List<ActorModel> = listOf(ActorModel(), ActorModel()),
    val boxOffice: BoxOfficeModel = BoxOfficeModel(),
    val similarMovies: List<SimilarMovieModel> = emptyList()
) {

    data class ActorModel(
        val actorId: String = "",
        val name: String = "Имя отсутствует",
        val asCharacter: String = "Информация отстутствует",
        val image: String = "",
    )

    data class BoxOfficeModel(
        val budget: String = "",
        val worldwideGross: String = "",
        val grossUSA: String = ""
    )

    data class SimilarMovieModel(
        val similarId: String = "",
        val title: String = "",
        val image: String = "",
        val imDbRating: String = "",
    )
}

fun MovieInfoResponse.toDomain() = MovieInfoModel(
    movieId = id.orEmpty(),
    title = title ?: "Без названия",
    description = descriptionRus ?: descriptionEng ?: "Описание отсутствует",
    year = year ?: "1900",
    imageUrl = image.orEmpty(),

    genres = genres ?: "Информация отсутствует",
    runtimeMins = runtimeMins ?: "0ч 0 мин", //TODO Спарсить минуты
    countries = countries ?: "Информация отсутствует",

    rating = imDbRating ?: "0.0",
    ratingVotes = imDbRatingVotes ?: "0",

    directors = directors ?: "Информация отсутствует",

    actors = actorList?.map { actor ->
        ActorModel(
            actorId = actor?.id.orEmpty(),
            name = actor?.name.orEmpty(),
            asCharacter = actor?.asCharacter.orEmpty(),
            image = actor?.image.orEmpty()
        )
    } ?: emptyList(),

    boxOffice = BoxOfficeModel(
        budget = boxOffice?.budget.orEmpty(),
        grossUSA = boxOffice?.grossUSA.orEmpty(),
        worldwideGross = boxOffice?.cumulativeWorldwideGross.orEmpty()
    ),

    similarMovies = similars?.map { similar ->
        SimilarMovieModel(
            similarId = similar?.id.orEmpty(),
            title = similar?.title.orEmpty(),
            image = similar?.image.orEmpty(),
            imDbRating = similar?.imDbRating ?: "0.0"
        )
    } ?: emptyList(),
)
