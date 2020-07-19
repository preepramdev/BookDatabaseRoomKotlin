package com.pram.bookdatabaseroomkotlin.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pram.bookapiretrofit.manager.Contextor
import com.pram.bookdatabaseroomkotlin.R
import com.pram.bookdatabaseroomkotlin.activity.UpdateActivity
import com.pram.bookdatabaseroomkotlin.databaseController
import com.pram.bookdatabaseroomkotlin.db.BookDatabaseCallBack
import com.pram.bookdatabaseroomkotlin.dialog.OneButtonDialogFragment
import com.pram.bookdatabaseroomkotlin.dialog.TwoButtonDialogFragment
import com.pram.bookdatabaseroomkotlin.model.Book
import kotlinx.android.synthetic.main.fragment_detail.view.*

/**
 * Created by nuuneoi on 11/16/2014.
 */
class DetailFragment : Fragment(), OneButtonDialogFragment.OnDialogListener, TwoButtonDialogFragment.OnDialogListener {
    private var mContext: Context? = null
    private var mRootView: View? = null
    private var mBook: Book? = null
    private var mBookId: Int? = null

    override fun onOneButtonClick() {
        requireActivity().finish()
    }

    override fun onTwoButtonPositiveClick() {
        removeBook()
    }

    override fun onTwoButtonNegativeClick() {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(savedInstanceState)
        savedInstanceState?.let { onRestoreInstanceState(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_detail, container, false)
        initInstances(rootView, savedInstanceState)
        return rootView
    }

    private fun init(savedInstanceState: Bundle?) {
        mContext = Contextor.instance!!.context
    }

    private fun initInstances(rootView: View, savedInstanceState: Bundle?) {
        mRootView = rootView

        if (arguments != null) {
            mBookId = arguments!!.getInt("bookId")
        }

        mRootView!!.apply {
            btnUpdate!!.apply {
                setOnClickListener {
                    val intent = Intent(mContext, UpdateActivity::class.java)
                    intent.putExtra("book", mBook)
                    startActivity(intent)
                }
            }

            btnRemove!!.apply {
                setOnClickListener {
                    callTwoButtonDialog("Remove?", "Ok", "Cancel")
                }
            }
        }

//        fetchDatabase();
    }

    private fun fetchDatabase() {
        databaseController.getBook(mBookId!!, object : BookDatabaseCallBack {
            override fun onCallBack(result: Any?) {
                mBook = result as Book
                if (mBook != null) {
                    val id: String = mBook!!.id.toString()
                    val title: String = mBook!!.title
                    val author: String = mBook!!.author
                    val page: String = mBook!!.pages

                    mRootView!!.apply {
                        tvBookId!!.text = id
                        tvBookTitle!!.text = title
                        tvBookAuthor!!.text = author
                        tvBookPages!!.text = page
                    }
                }
            }

        })
    }

    private fun removeBook() {
        databaseController.removeBook(mBookId!!, object : BookDatabaseCallBack {
            override fun onCallBack(result: Any?) {
                callOneButtonDialog("Removed", "Ok")
            }
        })
    }

    private fun callOneButtonDialog(message: String, submit: String) {
        val fragment = OneButtonDialogFragment.Builder()
                .setMessage(message)
                .setSubmit(submit)
                .build()
        fragment.show(childFragmentManager, "OneButtonDialogFragment")
    }

    private fun callTwoButtonDialog(message: String, positive: String, negative: String) {
        val fragment = TwoButtonDialogFragment.Builder()
                .setMessage(message)
                .setPositive(positive)
                .setNegative(negative)
                .build()
        fragment.show(childFragmentManager, "TwoButtonDialogFragment")
    }

    override fun onResume() {
        super.onResume()
        fetchDatabase()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save Instance State here
    }

    private fun onRestoreInstanceState(savedInstanceState: Bundle) { // Restore Instance State here
    }

    companion object {
        private const val TAG = "DetailFragment"
        fun newInstance(bookId: Int): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putInt("bookId", bookId)
            fragment.arguments = args
            return fragment
        }
    }
}