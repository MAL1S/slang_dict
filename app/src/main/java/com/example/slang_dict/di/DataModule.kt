package com.example.slang_dict.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.slang_dict.data.local.SlangDictDatabase
import com.example.slang_dict.data.local.SlangWordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideSlangDictDatabase(@ApplicationContext context: Context): SlangDictDatabase {
        return Room.databaseBuilder(
            context,
            SlangDictDatabase::class.java,
            "slang_dict_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideSlangWordDao(slangDictDatabase: SlangDictDatabase): SlangWordDao {
        return slangDictDatabase.getSlangWordDao()
    }
}