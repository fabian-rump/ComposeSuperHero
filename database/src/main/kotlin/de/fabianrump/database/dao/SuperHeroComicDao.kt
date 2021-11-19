package de.fabianrump.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.fabianrump.database.model.SuperHeroComic

@Dao
abstract class SuperHeroComicDao {

    @Query("SELECT * from superherocomic ORDER BY name ASC")
    abstract fun getAllSuperHeroComics(): LiveData<List<SuperHeroComic>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertComics(vararg superHeroComics: SuperHeroComic)
}