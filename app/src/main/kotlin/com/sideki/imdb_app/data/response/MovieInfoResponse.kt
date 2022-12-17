package com.sideki.imdb_app.data.response

import com.google.gson.annotations.SerializedName

data class MovieInfoResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("year") val year: String?, // Год выпуска
    @SerializedName("image") val image: String?, // Обложка

    // Описание
    @SerializedName("plot") val descriptionEng: String?, // Eng
    @SerializedName("plotLocal") val descriptionRus: String?, // Rus

    @SerializedName("genres") val genres: String?, // Жанры

    @SerializedName("imDbRating") val imDbRating: String?,
    @SerializedName("imDbRatingVotes") val imDbRatingVotes: String?, // Количесвто голосов

    @SerializedName("directors") val directors: String?, // Режисеры

    // Тайминги
    @SerializedName("runtimeMins") val runtimeMins: String?, // "127"

    @SerializedName("actorList") val actorList: List<ActorResponse?>?, // В ролях
    @SerializedName("countries") val countries: String?, //Страны
    @SerializedName("boxOffice") val boxOffice: BoxOfficeResponse?, // Бюджет и сборы
    @SerializedName("similars") val similars: List<SimilarResponse?>?, // Схожие фильмы

    @SerializedName("ratings") val ratings: RatingsResponse?,
    @SerializedName("images") val images: ImagesResponse?,
    @SerializedName("trailer") val trailer: TrailerResponse?,

    @SerializedName("errorMessage") val errorMessage: String?,
) {
    data class ActorResponse(
        @SerializedName("asCharacter") val asCharacter: String?,
        @SerializedName("id") val id: String?,
        @SerializedName("image") val image: String?,
        @SerializedName("name") val name: String?
    )

    data class BoxOfficeResponse(
        @SerializedName("budget") val budget: String?,
        @SerializedName("cumulativeWorldwideGross") val cumulativeWorldwideGross: String?,
        @SerializedName("grossUSA") val grossUSA: String?,
        @SerializedName("openingWeekendUSA") val openingWeekendUSA: String?
    )

    data class SimilarResponse(
        @SerializedName("id") val id: String?,
        @SerializedName("imDbRating") val imDbRating: String?,
        @SerializedName("image") val image: String?,
        @SerializedName("title") val title: String?
    )

    //Возможны косяки

    data class RatingsResponse(
        @SerializedName("imDb") val imDb: String?,
        @SerializedName("metacritic") val metacritic: String?,
        @SerializedName("theMovieDb") val theMovieDb: String?,
        @SerializedName("rottenTomatoes") val rottenTomatoes: String?,
        @SerializedName("filmAffinity") val filmAffinity: String?,
    )

    data class ImagesResponse(
        @SerializedName("items") val image: List<Image>?
    ) {
        data class Image(
            @SerializedName("title") val title: String?,
            @SerializedName("image") val image: String?
        )
    }

    data class TrailerResponse(
        @SerializedName("videoId") val videoId: String?,
        @SerializedName("videoTitle") val videoTitle: String?,
        @SerializedName("videoDescription") val videoDescription: String?,
        @SerializedName("thumbnailUrl") val thumbnailUrl: String?,
        @SerializedName("link") val link: String?,
        @SerializedName("linkEmbed") val linkEmbed: String?
    )
}
