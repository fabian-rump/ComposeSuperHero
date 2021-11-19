package de.fabianrump.network.model

import com.google.gson.annotations.SerializedName

data class ComicDataWrapper(
    @SerializedName("code") val code: String,
    @SerializedName("status") val status: String,
    @SerializedName("copyright") val copyright: String,
    @SerializedName("attributionText") val attributionText: String,
    @SerializedName("attributionHTML") val attributionHTML: String,
    @SerializedName("etag") val etag: String,
    @SerializedName("data") val data: ComicDataContainer
) {
    data class ComicDataContainer(
        @SerializedName("offset") val offset: Int,
        @SerializedName("limit") val limit: Int,
        @SerializedName("total") val total: Int,
        @SerializedName("count") val count: Int,
        @SerializedName("results") val results: List<Comic>
    ) {
        data class Comic(
            @SerializedName("id") val id: Int,
            @SerializedName("title") val title: String,
            @SerializedName("isbn") val isbn: String,
            @SerializedName("format") val format: String,
            @SerializedName("pageCount") val pageCount: Int,
            @SerializedName("resourceURI") val resourceURI: String
        )
    }
}