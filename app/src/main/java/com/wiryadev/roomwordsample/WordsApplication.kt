package com.wiryadev.roomwordsample

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {

    /**
     * Tidak perlu dibatalkan secara manual
     * karena akan dihancurkan bersama dengan proses yang menggunakannya
     */
    private val applicationScope = CoroutineScope(SupervisorJob())

    /**
     * Gunakan lazy agar database dan repository hanya dibuat ketika dibutuhkan
     * daripada selalu diinisialisasi saat aplikasi dimulai
     * sehingga mengurangi startup time
     */
    private val database by lazy {
        WordRoomDatabase.getDatabase(this, applicationScope)
    }
    val repository by lazy {
        WordRepository(database.wordDao())
    }

}