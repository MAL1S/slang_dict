package com.example.slang_dict.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class SlangWord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val word: String,
    val translates: String
): Parcelable
