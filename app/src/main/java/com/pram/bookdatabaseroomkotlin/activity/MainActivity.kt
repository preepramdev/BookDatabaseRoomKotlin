package com.pram.bookdatabaseroomkotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pram.bookdatabaseroomkotlin.R
import com.pram.bookdatabaseroomkotlin.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initInstances()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.contentContainer, MainFragment.newInstance())
                .commit()
        }
    }

    private fun initInstances() {}
}
