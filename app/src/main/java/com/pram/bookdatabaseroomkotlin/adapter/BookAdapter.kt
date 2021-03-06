package com.pram.bookdatabaseroomkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pram.bookdatabaseroomkotlin.viewholder.BookViewHolder
import com.pram.bookdatabaseroomkotlin.R
import com.pram.bookdatabaseroomkotlin.model.Book

class BookAdapter(private val mModels: MutableList<Book?>?) : RecyclerView.Adapter<BookViewHolder>() {

    private var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        mOnItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val model = mModels!![position]

        holder.apply {
            setTvBookId(model!!.id.toString())
            setTvBookTitle(model.title)
            setTvBookAuthor(model.author)
            setTvBookPage(model.pages)
            itemView.setOnClickListener {
                if (mOnItemClickListener != null) {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        mOnItemClickListener!!.onItemClick(position)
                    }
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return mModels!!.size
    }

}