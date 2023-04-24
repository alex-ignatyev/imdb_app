package com.sideki.imdb_app.model.model

import com.sideki.imdb_app.model.model.MovieInfoModel.ActorModel
import com.sideki.imdb_app.model.model.MovieInfoModel.BoxOfficeModel
import com.sideki.imdb_app.model.model.MovieInfoModel.ImageModel
import com.sideki.imdb_app.model.model.MovieInfoModel.RatingsModel
import com.sideki.imdb_app.model.model.MovieInfoModel.SimilarMovieModel
import com.sideki.imdb_app.model.model.MovieInfoModel.TrailerModel
import com.sideki.imdb_app.model.response.MovieInfoResponse

data class MovieInfoModel(
    val movieId: String = "",
    val title: String = "Без названия",
    val description: String = "Описание отсутствует",
    val year: String = "1900",
    val imageUrl: String = "",
    val genres: String = "Информация отстутствует",
    val runtimeMins: String = "0",
    val countries: String = "Информация отстутствует",
    val directors: String = "Информация отстутствует",
    val images: List<ImageModel> = emptyList(),
    val actors: List<ActorModel> = emptyList(),
    val similarMovies: List<SimilarMovieModel> = emptyList(),
    val ratings: RatingsModel = RatingsModel(),

    val boxOffice: BoxOfficeModel = BoxOfficeModel(),
    val trailer: TrailerModel = TrailerModel(),
) {

    data class ActorModel(
        val actorId: String = "",
        val name: String = "Имя отсутствует",
        val asCharacter: String = "Информация отстутствует",
        val image: String = "",
    )

    data class BoxOfficeModel(
        val budget: String = "0",
        val worldwideGross: String = "0",
        val grossUSA: String = "0"
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
        val imageUrl: String = ""
    )

    data class TrailerModel(
        val videoId: String = "",
        val videoTitle: String = "Информация отсутствует",
        val videoDescription: String = "Информация отсутствует",
        val thumbnailUrl: String = "",
        val link: String = "",
        val linkEmbed: String = ""
    )
}

fun MovieInfoResponse.toModel() = MovieInfoModel(
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
        budget = boxOffice?.budget ?: "0",
        grossUSA = boxOffice?.grossUSA ?: "0",
        worldwideGross = boxOffice?.cumulativeWorldwideGross ?: "0"
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
            imageUrl = it.image.orEmpty()
        )
    } ?: emptyList(),

    trailer = TrailerModel(
        videoId = trailer?.videoId ?: "",
        videoTitle = trailer?.videoTitle ?: "Информация отсутствует",
        videoDescription = trailer?.videoDescription ?: "Информация отсутствует",
        thumbnailUrl = trailer?.thumbnailUrl ?: "",
        link = trailer?.link ?: "",
        linkEmbed = trailer?.linkEmbed ?: ""
    ),
)
