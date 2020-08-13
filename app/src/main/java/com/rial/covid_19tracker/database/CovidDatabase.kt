package com.rial.covid_19tracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseCountry::class], version = 1, exportSchema = false)
// The classis abstract because Room creates the implementation
abstract class CovidDatabase : RoomDatabase() {

    // declaring abstract value that returns the CountryDao
    abstract val countryDao: CountryDao

}

private lateinit var INSTANCE: CovidDatabase

fun getDatabase(context: Context): CovidDatabase {
    synchronized(CovidDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                CovidDatabase::class.java,
                "countries").build()
        }
    }
    return INSTANCE
}