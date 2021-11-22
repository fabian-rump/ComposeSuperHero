package de.fabianrump.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

@Entity
data class SuperHeroComic(
    @PrimaryKey val id: Int = 0,
    val name: String,
    val resourceUri: String,
    val superHeroId: String,
    val isbn: String = "",
    val format: String = "",
    val pageCount: String = "",
    val thumbnail: String = "",
    val characters: List<String> = listOf()
)

class Converters {

    @TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}