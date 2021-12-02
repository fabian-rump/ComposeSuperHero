package de.fabianrump.database.repository

import androidx.lifecycle.LiveData
import de.fabianrump.database.model.Configuration

interface ConfigurationRepository {

    suspend fun insertConfiguration(configuration: Configuration)

    suspend fun findConfiguration(): Configuration?

    suspend fun getLiveConfiguration(): LiveData<Configuration?>

    suspend fun updateSystemColor(color: Int)
}