package com.pram.bookdatabaseroomkotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pram.bookdatabaseroomkotlin.R
import com.pram.bookdatabaseroomkotlin.fragment.DetailFragment

class DetailActivity : AppCompatActivity() {

    private var bookId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initInstances()

        if (intent != null) {
            bookId = intent.getIntExtra("bookId", 0)
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.contentContainer, DetailFragment.newInstance(bookId))
                .commit()
        }
    }

    private fun initInstances() {}
}
