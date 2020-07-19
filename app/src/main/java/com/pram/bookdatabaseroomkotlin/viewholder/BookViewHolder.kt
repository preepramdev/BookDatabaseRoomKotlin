package com.pram.bookdatabaseroomkotlin.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pram.bookdatabaseroomkotlin.R

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvBookId: TextView
    private val tvBookTitle: TextView
    private val tvBookAuthor: TextView
    private val tvBookPage: TextView

    init {
        tvBookId = itemView.findViewById(R.id.tvBookId)
        tvBookTitle = itemView.findViewById(R.id.tvBookTitle)
        tvBookAuthor = itemView.findViewById(R.id.tvBookAuthor)
        tvBookPage = itemView.findViewById(R.id.tvBookPages)
    }

    fun setTvBookId(bookId: String?) {
        tvBookId.apply {
            text = bookId
        }
    }

    fun setTvBookTitle(bookTitle: String?) {
        tvBookTitle.apply {
            text = bookTitle
        }
    }

    fun setTvBookAuthor(bookAuthor: String?) {
        tvBookAuthor.apply {
            text = bookAuthor
        }
    }

    fun setTvBookPage(bookPage: String?) {
        tvBookPage.apply {
            text = bookPage
        }
    }
}