package com.example.slang_dict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.slang_dict.data.local.SlangWord
import com.example.slang_dict.data.repository.SlangWordRepository
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: SlangWordRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        runBlocking {
            val words = repository.getAllSlangWords().first()
            Log.d("anime", "$words")
            if (words.isEmpty()) {
                val newWords = mutableListOf<SlangWord>()
                Log.d("anime", "get here")
                val inputStream = this@MainActivity.assets.open("dict.csv")

                csvReader().open(inputStream) {
                    readAllWithHeaderAsSequence().forEach {
                        val word = SlangWord(
                            word = it["word"]!!,
                            translates = it["translates"]!!
                        )

                        newWords.add(word)
                    }
                }

                repository.insertSlangWords(newWords.sortedWith(compareBy { it.word }))
            }
        }

        setContentView(R.layout.activity_main)
    }
}