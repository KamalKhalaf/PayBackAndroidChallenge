package com.example.cache.room

import androidx.room.*
import com.example.domain.entity.ImagesPixabayList

/**
 * @Created by: Kamal.Farghali
 * @Date: 08/02/2023 : 4:24 PM
 */


@Dao
internal interface ImagesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg movies: ImagesPixabayList)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(vararg movies: ImagesPixabayList)

    @Query("SELECT * FROM images WHERE q = : search")
    suspend fun getImages(search: String) : ImagesPixabayList?

    @Transaction
    suspend fun insertOrUpdate(vararg images: ImagesPixabayList) {
        insert(*images)
        update(*images)
    }
}
