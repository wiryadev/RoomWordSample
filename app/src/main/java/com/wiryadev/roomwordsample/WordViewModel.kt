package com.wiryadev.roomwordsample

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository) : ViewModel() {

    /**
     * Menggunakan LiveData dan meng-cache data yang dikembalikan [allWords] memiliki keuntungan:
     * - Kita bisa meletakkan observer/pengamat pada datanya
     *   dan hanya memperbarui UI ketika datanya benar benar berubah
     * - Repository menjadi terpisah dari UI melalui ViewModel
     */
    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    /**
     * Meluncurkan coroutine baru untuk memasukkan data secara async
     */
    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }
}

class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}