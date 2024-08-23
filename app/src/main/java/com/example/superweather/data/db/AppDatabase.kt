package com.example.superweather.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.superweather.data.db.dao.WeatherDao

@Database(entities = [WeatherEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    //really good way to only instantiate the db only once.
    companion object {
        //Singleton
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).addMigrations(MIGRATION_1_2).build()
                INSTANCE = instance
                return instance
            }
        }
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Add SQL statements to migrate from version 1 to 2
                database.execSQL("ALTER TABLE weather ADD COLUMN date INTEGER")
            }
        }
    }
}