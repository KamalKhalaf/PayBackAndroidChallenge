package com.example.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.entity.ImagesPixabayList

/**
 * @Created by: Kamal.Farghali
 * @Date: 08/02/2023 : 4:22 PM
 */


@Database(
    entities = [ImagesPixabayList::class],
    version = 1,
    exportSchema = false
)
internal abstract class ImagesdbDatabase : RoomDatabase() {
    abstract fun imagesDao(): ImagesPixabayList
}