package com.rial.covid_19tracker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Country::class], version = 1, exportSchema = false)
// The classis abstract because Room creates the implementation
abstract class CovidDatabase : RoomDatabase() {

    // declaring abstract value that returns the CountryDao
    abstract val countryDao: CountryDao

    // The companion object allows clients to access the methods for creating or getting the database without instantiating the class
    companion object {

        //The value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory.
        //This helps make sure the value of INSTANCE is always up-to-date and the same to all execution threads.
        @Volatile
        private var INSTANCE: CovidDatabase? = null

        fun getInstance(context: Context): CovidDatabase {
            //Wrapping the code to get the database into synchronized means that only one thread of execution at a time can enter this block of code,
            //which makes sure the database only gets initialized once.
            synchronized(this) {
                // Copy the current value of INSTANCE to a local variable instance.
                // This is to take advantage of smart cast, which is only available to local variables.
                var instance = INSTANCE

                if (instance == null) {
                    // If instance is null, use the database builder to get a database
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CovidDatabase::class.java,
                        "covid_database"
                    )
                            // migration strategy
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }



}