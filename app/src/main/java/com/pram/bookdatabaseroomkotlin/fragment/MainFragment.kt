package com.pram.bookdatabaseroomkotlin.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pram.bookapiretrofit.manager.Contextor
import com.pram.bookdatabaseroomkotlin.adapter.BookAdapter
import com.pram.bookdatabaseroomkotlin.R
import com.pram.bookdatabaseroomkotlin.activity.AddActivity
import com.pram.bookdatabaseroomkotlin.activity.DetailActivity
import com.pram.bookdatabaseroomkotlin.databaseController
import com.pram.bookdatabaseroomkotlin.db.BookDatabaseCallBack
import com.pram.bookdatabaseroomkotlin.model.Book
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.util.*

/**
 * Created by nuuneoi on 11/16/2014.
 */
class MainFragment : Fragment() {
    private var mContext: Context? = null
    private var mRootView: View? = null
    private var mModels: MutableList<Book?>? = null
    private var mAdapter: BookAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(savedInstanceState)
        savedInstanceState?.let { onRestoreInstanceState(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        initInstances(rootView, savedInstanceState)
        return rootView
    }

    private fun init(savedInstanceState: Bundle?) {
        mContext = Contextor.instance!!.context
    }

    private fun initInstances(rootView: View, savedInstanceState: Bundle?) {
        mRootView = rootView
        mModels = ArrayList()
        mAdapter = BookAdapter(mModels)
        mLayoutManager = LinearLayoutManager(mContext)

        mRootView!!.apply {
            rvBookList!!.apply {
                layoutManager = mLayoutManager
                setHasFixedSize(true)
                adapter = mAdapter
            }

            fabAdd!!.apply {
                setOnClickListener {
                    val intent = Intent(mContext, AddActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        mAdapter!!.setOnItemClickListener(object : BookAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.e(TAG, "onItemClick: $position")
                val intent = Intent(mContext, DetailActivity::class.java)
                intent.putExtra("bookId", mModels!![position]!!.id)
                startActivity(intent)
            }

        })

//        fetchDatabase();
    }

    private fun fetchDatabase() {
        databaseController.getAllBooks(object : BookDatabaseCallBack {
            override fun onCallBack(result: Any?) {
                val returnBooks = result as List<Book>
                mModels!!.addAll(returnBooks)
            }
        })
        mAdapter!!.notifyDataSetChanged() /*Adapter must notify outside*/
    }

    override fun onResume() {
        super.onResume()
        mModels!!.clear()
        fetchDatabase()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private fun onRestoreInstanceState(savedInstanceState: Bundle) {}

    companion object {
        private const val TAG = "MainFragment"
        fun newInstance(): MainFragment {
            val fragment = MainFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}