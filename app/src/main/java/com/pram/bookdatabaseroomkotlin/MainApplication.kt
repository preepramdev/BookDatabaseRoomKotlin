package com.pram.bookdatabaseroomkotlin

import android.app.Application
import com.pram.bookapiretrofit.manager.Contextor
import com.pram.bookdatabaseroomkotlin.db.BookDatabaseController
import com.pram.bookdatabaseroomkotlin.db.BookManager

val databaseController: BookDatabaseController by lazy {
    MainApplication.databaseController!!
}

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //Initialize thing(s) here
        Contextor.instance!!.init(applicationContext)
        BookManager.instance!!.init(applicationContext)
        databaseController = BookDatabaseController()
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    companion object {
        var databaseController: BookDatabaseController? = null
    }
}