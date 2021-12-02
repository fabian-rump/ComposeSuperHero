package de.fabianrump.database.repository

import androidx.lifecycle.LiveData
import de.fabianrump.database.dao.ConfigurationDao
import de.fabianrump.database.model.Configuration

class ConfigurationRepositoryImpl(
    private val configurationDao: ConfigurationDao
) : ConfigurationRepository {

    override suspend fun insertConfiguration(configuration: Configuration) {
        configurationDao.insertConfiguration(configuration)
    }

    override suspend fun findConfiguration(): Configuration? = configurationDao.findConfiguration()

    override suspend fun getLiveConfiguration(): LiveData<Configuration?> = configurationDao.getLiveConfiguration()

    override suspend fun updateSystemColor(color: Int) {
        configurationDao.updateSystemColor(color)
    }
}