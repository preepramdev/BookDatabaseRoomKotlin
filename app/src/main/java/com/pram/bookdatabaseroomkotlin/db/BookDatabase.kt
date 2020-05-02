package com.pram.bookdatabaseroomkotlin.db

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pram.bookdatabaseroomkotlin.model.Book

@Database(entities = [Book::class], version = 1)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        private var instance: BookDatabase? = null

        @Synchronized
        fun getInstance(context: Context?): BookDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, BookDatabase::class.java, "book.db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            }
            return instance
        }

        private val roomCallback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(
                    instance
                ).execute()
            }
        }
    }

    private class PopulateDbAsyncTask internal constructor(db: BookDatabase?) :
        AsyncTask<Void?, Void?, Void?>() {
        private val bookDao: BookDao = db!!.bookDao()

        override fun doInBackground(vararg params: Void?): Void? {
            bookDao.createBook(Book(1, ".NET Multithreading", "Alan Dennis", "360"))
            bookDao.createBook(Book(2, "Unix Basic", "W. John Snow", "126"))
            bookDao.createBook(Book(3, "Hello! Python", "Anthony Briggs", "352"))
            bookDao.createBook(Book(4, "Software Requirements", "Benjamin L. Kovitz", "448"))
            return null
        }
    }
}