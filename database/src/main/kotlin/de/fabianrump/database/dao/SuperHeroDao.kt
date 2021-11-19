package de.fabianrump.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import de.fabianrump.database.model.SuperHero
import de.fabianrump.database.model.SuperHeroComic
import de.fabianrump.database.model.SuperHeroWithComics

@Dao
abstract class SuperHeroDao {

    @Query("SELECT * from superhero WHERE id = :id")
    abstract fun getSuperHeroWithComic(id: String): LiveData<SuperHeroWithComics>

    @Query("SELECT COUNT(id) FROM superhero")
    abstract fun getSuperHeroesCount(): Int

    @Query("SELECT * from superhero ORDER BY name ASC")
    abstract fun getAllSuperHeroes(): LiveData<List<SuperHero>>

    @Transaction
    open suspend fun insertSuperHeroesWithComics(vararg superHeroesWithComics: SuperHeroWithComics){
        val superHeroes = superHeroesWithComics.map { it.superHero }.toTypedArray()
        val comics = superHeroesWithComics.map { it.comics }.flatten().toTypedArray()

        insertSuperHeroes(*superHeroes)
        insertComics(*comics)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insertSuperHeroes(vararg superHeroes: SuperHero)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insertComics(vararg superHeroComics: SuperHeroComic)

    @Query("DELETE FROM superhero")
    abstract suspend fun deleteAll()
}