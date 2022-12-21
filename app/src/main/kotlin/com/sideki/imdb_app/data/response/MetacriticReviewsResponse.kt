package com.sideki.imdb_app.data.response


import com.google.gson.annotations.SerializedName

data class MetacriticReviewsResponse(
    @SerializedName("errorMessage") val errorMessage: String?,
    @SerializedName("fullTitle") val fullTitle: String?,
    @SerializedName("imDbId") val imDbId: String?,
    @SerializedName("items") val items: List<Item?>?,
    @SerializedName("title") val title: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("year") val year: String?
){
    data class Item(
        @SerializedName("author") val author: String?,
        @SerializedName("content") val content: String?,
        @SerializedName("link") val link: String?,
        @SerializedName("publisher") val publisher: String?,
        @SerializedName("rate") val rate: String?
    )
}
