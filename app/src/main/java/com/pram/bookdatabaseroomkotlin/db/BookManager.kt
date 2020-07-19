package com.pram.bookdatabaseroomkotlin.db

import android.content.Context
import com.pram.bookdatabaseroomkotlin.db.BookDatabase.Companion.getInstance

/**
 * Created by nuuneoi on 12/6/14 AD.
 */
class BookManager private constructor() {
    var bookDatabase: BookDatabase? = null
        private set

    fun init(context: Context?) {
        bookDatabase = getInstance(context)
    }

    companion object {
        var instance: BookManager? = null
            get() {
                if (field == null) {
                    field = BookManager()
                }
                return field
            }
            private set
    }
}