package com.example.slang_dict.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SlangWord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val word: String,
    val translates: List<String>
)
