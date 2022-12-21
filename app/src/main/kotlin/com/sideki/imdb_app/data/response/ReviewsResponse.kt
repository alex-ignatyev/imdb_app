package com.sideki.imdb_app.data.response


import com.google.gson.annotations.SerializedName

data class ReviewsResponse(
    @SerializedName("errorMessage") val errorMessage: String?,
    @SerializedName("fullTitle") val fullTitle: String?,
    @SerializedName("imDbId") val imDbId: String?,
    @SerializedName("items") val items: List<Item?>?,
    @SerializedName("title") val title: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("year") val year: String?
){
    data class Item(
        @SerializedName("content") val content: String?,
        @SerializedName("date") val date: String?,
        @SerializedName("helpful") val helpful: String?,
        @SerializedName("rate") val rate: String?,
        @SerializedName("reviewLink") val reviewLink: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("userUrl") val userUrl: String?,
        @SerializedName("username") val username: String?,
        @SerializedName("warningSpoilers") val warningSpoilers: Boolean?
    )
}
