package com.example.slang_dict.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SlangWord::class], version = 0)
abstract class SlangDictDatabase: RoomDatabase() {

    abstract fun getSlangWordDao(): SlangWordDao
}