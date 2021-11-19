package de.fabianrump.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import de.fabianrump.database.model.MarvelAttribution

@Dao
abstract class MarvelAttributionDao {
    @Query("SELECT * from marvelattribution")
    abstract fun getMarvelAttributions(): LiveData<List<MarvelAttribution>>

    @Transaction
    open suspend fun updateAttribution(marvelAttribution: MarvelAttribution) {
        deleteAll()
        insertAttribution(marvelAttribution)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insertAttribution(marvelAttribution: MarvelAttribution)

    @Query("DELETE FROM marvelattribution")
    protected abstract suspend fun deleteAll()
}