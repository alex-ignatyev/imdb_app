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

    @SerializedName("trailer") val trailer: Any?, //TODO найти хоть 1 фильм с трейлером

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
}