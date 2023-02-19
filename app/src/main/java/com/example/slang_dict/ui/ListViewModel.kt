package com.example.slang_dict.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slang_dict.data.local.SlangWord
import com.example.slang_dict.data.repository.SlangWordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: SlangWordRepository
) : ViewModel() {

    private val _slangWords: MutableStateFlow<List<SlangWord>> = MutableStateFlow(emptyList())
    val slangWords = _slangWords.asStateFlow()

    fun getAllWords() {
        viewModelScope.launch {
            repository.getAllSlangWords().collect {
                _slangWords.value = it
            }
        }
    }

    fun getFilteredWords(filter: String) {
        viewModelScope.launch {
            repository.getAllSlangWords().collect {
                _slangWords.value = it.filter { word ->
                    word.word.lowercase().contains(filter.lowercase())
                            || word.translates.lowercase().contains(filter.lowercase())
                }
            }
        }
    }
}