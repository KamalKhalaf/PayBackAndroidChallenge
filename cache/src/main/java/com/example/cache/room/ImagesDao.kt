package com.example.cache.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * @Created by: Kamal.Farghali
 * @Date: 08/02/2023 : 4:24 PM
 */


@Dao
interface ImagesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg images: ImageModel?)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(vararg images: ImageModel)

    @Query("SELECT * FROM image_table")
    suspend fun getImages() : List<ImageModel>

    @Transaction
    suspend fun insertOrUpdate(vararg images: ImageModel) {
        insert(*images)
        update(*images)
    }
}
