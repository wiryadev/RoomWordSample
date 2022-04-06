package com.wiryadev.roomwordsample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [
        Word::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.wordDao())
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            // Menghapus keseluruhan konten
            wordDao.deleteAll()

            // Menambahkan contoh kata (pre-populate).
            var word = Word("Hello")
            wordDao.insert(word)
            word = Word("World!")
            wordDao.insert(word)
            word = Word("Android")
            wordDao.insert(word)
            word = Word("Room")
            wordDao.insert(word)
        }
    }

    companion object {
        /**
         * Singleton mencegah lebih dari satu instance tercipta
         * di waktu yang bersamaan
         */
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope,
        ): WordRoomDatabase {
            /**
             * Jika [INSTACE] tidak null, dapat langsung dikembalikan.
             * Jika masih null, diinisialisasi dulu.
             */
            return INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        WordRoomDatabase::class.java,
                        "word_database"
                    )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // mengembalikan instance
                instance
            }
        }
    }
}