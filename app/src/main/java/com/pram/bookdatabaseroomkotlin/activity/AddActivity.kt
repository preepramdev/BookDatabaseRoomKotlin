package com.pram.bookdatabaseroomkotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pram.bookdatabaseroomkotlin.R
import com.pram.bookdatabaseroomkotlin.fragment.AddFragment

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        initInstances()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.contentContainer, AddFragment.newInstance())
                .commit()
        }
    }

    private fun initInstances() {}
}
