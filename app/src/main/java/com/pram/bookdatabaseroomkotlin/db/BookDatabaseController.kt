package com.pram.bookdatabaseroomkotlin.db

import android.content.Context
import android.util.Log
import com.pram.bookapiretrofit.manager.Contextor.Companion.instance
import com.pram.bookdatabaseroomkotlin.model.Book

class BookDatabaseController {
    private val TAG = "BookDatabaseController"
    private val mContext: Context?
    private var databaseManager: BookDao? = null
    private val emptyResult = Any()

    init {
        mContext = instance!!.context
        databaseManager = BookManager.instance!!.bookDatabase!!.bookDao()
    }

    fun getAllBooks(callBack: BookDatabaseCallBack) {
        val thread: Thread = object : Thread() {
            override fun run() {
                super.run()
                val books = databaseManager!!.allBooks
                books!!.forEach { book ->
                    Log.e(TAG, "-->")
                    Log.e(TAG, "fetchDatabase bookId: " + book!!.id)
                    Log.e(TAG, "fetchDatabase bookTitle: " + book.title)
                    Log.e(TAG, "fetchDatabase bookAuthor: " + book.author)
                    Log.e(TAG, "fetchDatabase bookPages: " + book.pages)
                    Log.e(TAG, "<--")
                }
                callBack.onCallBack(books)
            }
        }
        thread.start()
    }

    fun getBook(bookId: Int, callBack: BookDatabaseCallBack) {
        val thread: Thread = object : Thread() {
            override fun run() {
                super.run()
                val book = databaseManager!!.getBook(bookId)
                callBack.onCallBack(book)
            }
        }
        thread.start()
    }

    fun createBook(book: Book?, callBack: BookDatabaseCallBack) {
        val thread: Thread = object : Thread() {
            override fun run() {
                super.run()
                databaseManager!!.createBook(book)
                callBack.onCallBack(emptyResult) // void
            }
        }
        thread.start()
    }

    fun updateBook(book: Book?, callBack: BookDatabaseCallBack) {
        val thread: Thread = object : Thread() {
            override fun run() {
                super.run()
                databaseManager!!.updateBook(book)
                callBack.onCallBack(emptyResult) // void
            }
        }
        thread.start()
    }

    fun removeBook(bookId: Int, callBack: BookDatabaseCallBack) {
        val thread: Thread = object : Thread() {
            override fun run() {
                super.run()
                databaseManager!!.removeBook(bookId)
                callBack.onCallBack(emptyResult) // void
            }
        }
        thread.start()
    }
}