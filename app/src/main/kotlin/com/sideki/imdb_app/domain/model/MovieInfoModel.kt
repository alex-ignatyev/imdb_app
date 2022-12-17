package com.sideki.imdb_app.domain.model

import com.sideki.imdb_app.data.response.MovieInfoResponse
import com.sideki.imdb_app.domain.model.MovieInfoModel.ActorModel
import com.sideki.imdb_app.domain.model.MovieInfoModel.BoxOfficeModel
import com.sideki.imdb_app.domain.model.MovieInfoModel.ImageModel
import com.sideki.imdb_app.domain.model.MovieInfoModel.RatingsModel
import com.sideki.imdb_app.domain.model.MovieInfoModel.SimilarMovieModel
import com.sideki.imdb_app.domain.model.MovieInfoModel.TrailerModel

data class MovieInfoModel(
    val movieId: String = "",
    val title: String = "Без названия",
    val description: String = "Описание отсутствует",
    val year: String = "1900",
    val imageUrl: String = "",
    val genres: String = "боевик, комедия",
    val runtimeMins: String = "127",
    val countries: String = "США",
    val directors: String = "Информация отстутствует",
    val actors: List<ActorModel> = listOf(ActorModel(), ActorModel()),
    val boxOffice: BoxOfficeModel = BoxOfficeModel(),
    val similarMovies: List<SimilarMovieModel> = emptyList(),
    val ratings: RatingsModel = RatingsModel(),
    val images: List<ImageModel> = emptyList(),
    val trailer: TrailerModel = TrailerModel(),
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
        val title: String = "Название фильма",
        val image: String = "",
        val imDbRating: String = "0.0",
    )

    data class RatingsModel(
        val imDb: String = "0.0",
        val imDbVotes: String = "0", //TODO Парсить количество
        val metacritic: String = "0",
        val theMovieDb: String = "0.0",
        val rottenTomatoes: String = "0",
        val filmAffinity: String = "0.0"
    )

    data class ImageModel(
        val title: String = "",
        val image: String = ""
    )

    data class TrailerModel(
        val videoId: String = "",
        val videoTitle: String = "",
        val videoDescription: String = "",
        val thumbnailUrl: String = "",
        val link: String = "",
        val linkEmbed: String = ""
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

    ratings = RatingsModel(
        imDb = ratings?.imDb ?: "0.0",
        imDbVotes = imDbRatingVotes ?: "0",
        metacritic = ratings?.metacritic ?: "0",
        theMovieDb = ratings?.theMovieDb ?: "0.0",
        rottenTomatoes = ratings?.rottenTomatoes ?: "0",
        filmAffinity = ratings?.filmAffinity ?: "0.0"
    ),

    //TODO Добавлять в список только если image не пустой
    images = images?.image?.map {
        ImageModel(
            title = it.title.orEmpty(),
            image = it.image.orEmpty()
        )
    } ?: emptyList(),

    trailer = TrailerModel(
        videoId = trailer?.videoId ?: "",
        videoTitle = trailer?.videoTitle ?: "",
        videoDescription = trailer?.videoDescription ?: "",
        thumbnailUrl = trailer?.thumbnailUrl ?: "",
        link = trailer?.link ?: "",
        linkEmbed = trailer?.linkEmbed ?: ""
    ),
)
