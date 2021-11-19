package de.fabianrump.network.model

import com.google.gson.annotations.SerializedName

data class CharacterDataWrapper (
    @SerializedName("code") val code : String,
    @SerializedName("status") val status : String,
    @SerializedName("copyright") val copyright : String,
    @SerializedName("attributionText") val attributionText : String,
    @SerializedName("attributionHTML") val attributionHTML : String,
    @SerializedName("data") val data : CharacterDataContainer,
    @SerializedName("etag") val etag : String
) {
    data class CharacterDataContainer (
        @SerializedName("offset") val offset : Int,
        @SerializedName("limit") val limit : Int,
        @SerializedName("total") val total : Int,
        @SerializedName("count") val count : Int,
        @SerializedName("results") val results : List<Character>
    ) {
        data class Character (
            @SerializedName("id") val id : String,
            @SerializedName("name") val name : String,
            @SerializedName("description") val description : String,
            @SerializedName("modified") val modified : String,
            @SerializedName("resourceURI") val resourceURI : String,
            @SerializedName("urls") val urls : List<Url>,
            @SerializedName("thumbnail") val thumbnail : Image,
            @SerializedName("comics") val comics : Comics,
            @SerializedName("stories") val stories : Stories,
            @SerializedName("events") val events : Events,
            @SerializedName("series") val series : Series
        ) {
            data class Url (
                @SerializedName("type") val type : String,
                @SerializedName("url") val url : String
            )

            data class Image (
                @SerializedName("path") val path : String,
                @SerializedName("extension") val extension : String
            )

            data class Comics (
                @SerializedName("available") val available : String,
                @SerializedName("returned") val returned : String,
                @SerializedName("collectionURI") val collectionURI : String,
                @SerializedName("items") val items : List<Item>
            )

            data class Stories (
                @SerializedName("available") val available : String,
                @SerializedName("returned") val returned : String,
                @SerializedName("collectionURI") val collectionURI : String,
                @SerializedName("items") val items : List<Item>
            )

            data class Events (
                @SerializedName("available") val available : String,
                @SerializedName("returned") val returned : String,
                @SerializedName("collectionURI") val collectionURI : String,
                @SerializedName("items") val items : List<Item>
            )

            data class Series (
                @SerializedName("available") val available : String,
                @SerializedName("returned") val returned : String,
                @SerializedName("collectionURI") val collectionURI : String,
                @SerializedName("items") val items : List<Item>
            )

            data class Item (
                @SerializedName("resourceURI") val resourceURI : String,
                @SerializedName("name") val name : String
            )
        }
    }
}