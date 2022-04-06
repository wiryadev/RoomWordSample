package com.wiryadev.roomwordsample

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

/**
 * Deklarasikan DAO sebagai properti di constructor.
 * Gunakan DAO sebagai dependency daripada menggunakan keseluruhan database,
 * karena seluruh fungsi yang diperlukan bisa diakses melalui DAO.
 */
class WordRepository(private val wordDao: WordDao) {

    /**
     * Room mengeksekusi seluruh query di thread yang terpisah.
     * Flow yang diamati akan otomatis memberitahu observer/pengamat
     * ketika data terlah berubah.
     */
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    /**
     * Secara default, Room menjalankan query yang ditandai sebagai suspend function di luar main thread,
     * oleh akrean itu, kita tidak perlu melakukan hal lain untuk memastikan operasi database tidak berada
     * di main thread.
     */
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

}