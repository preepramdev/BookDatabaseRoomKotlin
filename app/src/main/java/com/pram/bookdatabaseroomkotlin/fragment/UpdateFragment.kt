package com.pram.bookdatabaseroomkotlin.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pram.bookapiretrofit.manager.Contextor
import com.pram.bookdatabaseroomkotlin.R
import com.pram.bookdatabaseroomkotlin.databaseController
import com.pram.bookdatabaseroomkotlin.db.BookDatabaseCallBack
import com.pram.bookdatabaseroomkotlin.dialog.OneButtonDialogFragment
import com.pram.bookdatabaseroomkotlin.dialog.TwoButtonDialogFragment
import com.pram.bookdatabaseroomkotlin.model.Book
import kotlinx.android.synthetic.main.fragment_update.view.*

/**
 * Created by nuuneoi on 11/16/2014.
 */
class UpdateFragment : Fragment(), OneButtonDialogFragment.OnDialogListener, TwoButtonDialogFragment.OnDialogListener {
    private var mContext: Context? = null
    private var mRootView: View? = null
    private var mBook: Book? = null

    override fun onOneButtonClick() {
        requireActivity().finish()
    }

    override fun onTwoButtonPositiveClick() {
        requireActivity().finish()
    }

    override fun onTwoButtonNegativeClick() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(savedInstanceState)
        savedInstanceState?.let { onRestoreInstanceState(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_update, container, false)
        initInstances(rootView, savedInstanceState)
        return rootView
    }

    private fun init(savedInstanceState: Bundle?) {
        mContext = Contextor.instance!!.context
    }

    private fun initInstances(rootView: View, savedInstanceState: Bundle?) {
        mRootView = rootView

        if (arguments != null) {
            mBook = arguments!!.getParcelable("book")
        }

        mRootView!!.apply {
            var id = ""
            var title = ""
            var author = ""
            var pages = ""

            if (mBook != null) {
                id = mBook!!.id.toString()
                title = mBook!!.title
                author = mBook!!.author
                pages = mBook!!.pages
            }

            tvBookId!!.apply {
                text = id
            }

            edtBookTitle!!.apply {
                setText(title)
            }

            edtBookAuthor!!.apply {
                setText(author)
            }

            edtBookPages!!.apply {
                setText(pages)
            }

            btnUpdate!!.apply {
                setOnClickListener {
                    updateBook()
                }
            }
            btnCancel!!.apply {
                setOnClickListener {
                    callTwoButtonDialog("Leave?", "Ok", "Cancel")
                }
            }
        }
    }

    private fun updateBook() {
        val title = mRootView!!.edtBookTitle!!.text.toString()
        val author = mRootView!!.edtBookAuthor!!.text.toString()
        val pages = mRootView!!.edtBookPages!!.text.toString()

        mBook!!.title = title
        mBook!!.author = author
        mBook!!.pages = pages

        databaseController.updateBook(mBook!!, object : BookDatabaseCallBack {
            override fun onCallBack(result: Any?) {
                callOneButtonDialog("Updated", "Ok")
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

    private fun onRestoreInstanceState(savedInstanceState: Bundle) { // Restore Instance State here
    }

    companion object {
        private const val TAG = "UpdateFragment"
        fun newInstance(book: Book?): UpdateFragment {
            val fragment = UpdateFragment()
            val args = Bundle()
            args.putParcelable("book", book)
            fragment.arguments = args
            return fragment
        }
    }
}