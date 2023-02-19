package com.example.slang_dict.data.repository

import com.example.slang_dict.data.local.SlangWord
import com.example.slang_dict.data.local.SlangWordDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SlangWordRepository @Inject constructor(
    private val slangWordDao: SlangWordDao
) {

    fun getAllSlangWords() = slangWordDao.getAll()

    suspend fun insertSlangWords(slangWords: List<SlangWord>) {
        withContext(Dispatchers.IO) {
            slangWordDao.insert(slangWords)
        }
    }
}