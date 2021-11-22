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
            @SerializedName("resourceURI") val resourceURI: String,
            @SerializedName("thumbnail") val thumbnail: Image,
            @SerializedName("characters") val characters: CharacterList
        ) {

            data class Image(
                @SerializedName("path") val path: String,
                @SerializedName("extension") val extension: String
            )

            data class CharacterList(
                @SerializedName("available") val available: Int,
                @SerializedName("returned") val returned: Int,
                @SerializedName("collectionURI") val collectionURI: String,
                @SerializedName("items") val items: List<CharacterSummary>
            ) {
                data class CharacterSummary(
                    @SerializedName("resourceURI") val resourceURI: String,
                    @SerializedName("name") val name: String,
                    @SerializedName("role") val role: String,
                )
            }
        }
    }
}