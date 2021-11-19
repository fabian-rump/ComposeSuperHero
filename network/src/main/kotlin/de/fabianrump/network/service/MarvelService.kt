package de.fabianrump.network.service

import de.fabianrump.network.model.CharacterDataWrapper
import de.fabianrump.network.model.ComicDataWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {
    @GET("/v1/public/characters")
    suspend fun fetchCharacters(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 100
    ): Response<CharacterDataWrapper>

    @GET("/v1/public/characters/{characterId}/comics")
    suspend fun fetchComics(
        @Path("characterId") characterId: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 100
    ): Response<ComicDataWrapper>
}