package de.fabianrump.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.fabianrump.database.model.Configuration

@Dao
interface ConfigurationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConfiguration(configuration: Configuration)

    @Query("SELECT * FROM configuration LIMIT 1")
    suspend fun findConfiguration(): Configuration?

    @Query("SELECT * FROM configuration LIMIT 1")
    fun getLiveConfiguration(): LiveData<Configuration?>

    @Query("UPDATE configuration SET systemColor = :color")
    suspend fun updateSystemColor(color: Int)
}