package com.example.cache.di

import android.content.Context
import androidx.room.Room
import com.example.cache.room.ImagesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Created by: Kamal.Farghali
 * @Date: 11/02/2023 : 11:23 PM
 */

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context, callback : ImagesDatabase.Callback) =
        Room.databaseBuilder(appContext, ImagesDatabase::class.java, "payback_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()

    @Provides
    fun provideImagesDao(db: ImagesDatabase) = db.imagesDao()
}