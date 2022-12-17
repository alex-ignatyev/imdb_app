package com.sideki.imdb_app.data.response

import com.google.gson.annotations.SerializedName

data class MovieInfoResponse(
    @SerializedName("actorList") val actorList: List<Actor?>?,
    @SerializedName("awards") val awards: String?,
    @SerializedName("boxOffice") val boxOffice: BoxOffice?,
    @SerializedName("companies") val companies: String?,
    @SerializedName("companyList") val companyList: List<Company?>?,
    @SerializedName("contentRating") val contentRating: String?,
    @SerializedName("countries") val countries: String?,
    @SerializedName("countryList") val countryList: List<Country?>?,
    @SerializedName("directorList") val directorList: List<Director?>?,
    @SerializedName("directors") val directors: String?,
    @SerializedName("errorMessage") val errorMessage: Any?,
    @SerializedName("fullCast") val fullCast: Any?,
    @SerializedName("fullTitle") val fullTitle: String?,
    @SerializedName("genreList") val genreList: List<Genre?>?,
    @SerializedName("genres") val genres: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("imDbRating") val imDbRating: String?,
    @SerializedName("imDbRatingVotes") val imDbRatingVotes: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("images") val images: Any?,
    @SerializedName("keywordList") val keywordList: List<String?>?,
    @SerializedName("keywords") val keywords: String?,
    @SerializedName("languageList") val languageList: List<Language?>?,
    @SerializedName("languages") val languages: String?,
    @SerializedName("metacriticRating") val metacriticRating: String?,
    @SerializedName("originalTitle") val originalTitle: String?,
    @SerializedName("plot") val plot: String?,
    @SerializedName("plotLocal") val plotLocal: String?,
    @SerializedName("plotLocalIsRtl") val plotLocalIsRtl: Boolean?,
    @SerializedName("posters") val posters: Any?,
    @SerializedName("ratings") val ratings: Any?,
    @SerializedName("releaseDate") val releaseDate: String?,
    @SerializedName("runtimeMins") val runtimeMins: String?,
    @SerializedName("runtimeStr") val runtimeStr: String?,
    @SerializedName("similars") val similars: List<Similar?>?,
    @SerializedName("starList") val starList: List<Star?>?,
    @SerializedName("stars") val stars: String?,
    @SerializedName("tagline") val tagline: Any?,
    @SerializedName("title") val title: String?,
    @SerializedName("trailer") val trailer: Any?,
    @SerializedName("tvEpisodeInfo") val tvEpisodeInfo: Any?,
    @SerializedName("tvSeriesInfo") val tvSeriesInfo: Any?,
    @SerializedName("type") val type: String?,
    @SerializedName("wikipedia") val wikipedia: Any?,
    @SerializedName("writerList") val writerList: List<Writer?>?,
    @SerializedName("writers") val writers: String?,
    @SerializedName("year") val year: String?
) {
    data class Actor(
        @SerializedName("asCharacter") val asCharacter: String?,
        @SerializedName("id") val id: String?,
        @SerializedName("image") val image: String?,
        @SerializedName("name") val name: String?
    )

    data class BoxOffice(
        @SerializedName("budget") val budget: String?,
        @SerializedName("cumulativeWorldwideGross") val cumulativeWorldwideGross: String?,
        @SerializedName("grossUSA") val grossUSA: String?,
        @SerializedName("openingWeekendUSA") val openingWeekendUSA: String?
    )

    data class Company(
        @SerializedName("id") val id: String?,
        @SerializedName("name") val name: String?
    )

    data class Country(
        @SerializedName("key") val key: String?,
        @SerializedName("value") val value: String?
    )

    data class Director(
        @SerializedName("id") val id: String?,
        @SerializedName("name") val name: String?
    )

    data class Genre(
        @SerializedName("key") val key: String?,
        @SerializedName("value") val value: String?
    )

    data class Language(
        @SerializedName("key") val key: String?,
        @SerializedName("value") val value: String?
    )

    data class Similar(
        @SerializedName("id") val id: String?,
        @SerializedName("imDbRating") val imDbRating: String?,
        @SerializedName("image") val image: String?,
        @SerializedName("title") val title: String?
    )

    data class Star(
        @SerializedName("id") val id: String?,
        @SerializedName("name") val name: String?
    )

    data class Writer(
        @SerializedName("id") val id: String?,
        @SerializedName("name") val name: String?
    )
}
