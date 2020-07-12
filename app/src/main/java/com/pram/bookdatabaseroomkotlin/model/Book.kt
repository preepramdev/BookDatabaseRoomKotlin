package com.pram.bookdatabaseroomkotlin.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "book_list")
@Parcelize
data class Book(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "author")
    var author: String,
    @ColumnInfo(name = "pages")
    var pages: String
) : Parcelable {
    constructor(title: String,
                author: String,
                pages: String) : this(0, title, author, pages)
}