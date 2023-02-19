package com.example.slang_dict.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SlangWordDao {

    @Insert
    suspend fun insert(slangWord: SlangWord)

    @Insert
    suspend fun insert(slangWord: List<SlangWord>)

    @Query("SELECT * FROM slangWord")
    fun getAll(): Flow<List<SlangWord>>
}