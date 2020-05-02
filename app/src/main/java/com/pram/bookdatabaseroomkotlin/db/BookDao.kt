package com.pram.bookdatabaseroomkotlin.db

import androidx.room.*
import com.pram.bookdatabaseroomkotlin.model.Book

@Dao
interface BookDao {
    @get:Query("SELECT * FROM book_list")
    val allBooks: List<Book?>?

    @Query("SELECT * FROM book_list WHERE id = :bookId")
    fun getBook(bookId: Int): Book?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createBook(book: Book?) // When Insert is Success, this will return Primary Key

    @Update
    fun updateBook(book: Book?)

    /*@Delete
    fun delete(book: Book?)*/

    @Query("DELETE FROM book_list WHERE id = :bookId")
    fun removeBook(bookId: Int)
}