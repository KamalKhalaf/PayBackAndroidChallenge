package com.example.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import javax.inject.Inject
import javax.inject.Provider

/**
 * @Created by: Kamal.Farghali
 * @Date: 08/02/2023 : 4:22 PM
 */


@Database(
    entities = [ImageModel::class],
    version = 1,
    exportSchema = false
)
abstract class ImagesDatabase : RoomDatabase() {
    abstract fun imagesDao(): ImagesDao


    class Callback @Inject constructor(
        val database: Provider<ImagesDatabase>
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().imagesDao()
        }
    }
}