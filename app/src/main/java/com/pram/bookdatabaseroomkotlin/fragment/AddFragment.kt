package com.pram.bookdatabaseroomkotlin.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pram.bookapiretrofit.manager.Contextor
import com.pram.bookdatabaseroomkotlin.R
import com.pram.bookdatabaseroomkotlin.databaseController
import com.pram.bookdatabaseroomkotlin.db.BookDatabaseCallBack
import com.pram.bookdatabaseroomkotlin.dialog.OneButtonDialogFragment
import com.pram.bookdatabaseroomkotlin.dialog.TwoButtonDialogFragment
import com.pram.bookdatabaseroomkotlin.model.Book
import kotlinx.android.synthetic.main.fragment_add.view.*

/**
 * Created by nuuneoi on 11/16/2014.
 */
class AddFragment : Fragment(), OneButtonDialogFragment.OnDialogListener, TwoButtonDialogFragment.OnDialogListener {

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
        val rootView = inflater.inflate(R.layout.fragment_add, container, false)
        initInstances(rootView, savedInstanceState)
        return rootView
    }

    private fun init(savedInstanceState: Bundle?) {
        mContext = Contextor.instance!!.context
    }

    private fun initInstances(rootView: View, savedInstanceState: Bundle?) {
        mRootView = rootView

        mRootView!!.apply {
            btnAdd!!.apply {
                setOnClickListener {
                    addBook()
                }
            }

            btnCancel!!.apply {
                setOnClickListener {
                    callTwoButtonDialog("Leave?", "Ok", "Cancel")
                }
            }
        }
    }

    private fun addBook() {
        val title = mRootView!!.edtBookTitle!!.text.toString()
        val author = mRootView!!.edtBookAuthor!!.text.toString()
        val pages = mRootView!!.edtBookPages!!.text.toString()

        if (title.isEmpty() || author.isEmpty() || pages.isEmpty()) {
            Toast.makeText(mContext, "Check data", Toast.LENGTH_SHORT).show()
        } else {
            mBook = Book(title, author, pages)

            databaseController.createBook(mBook, object : BookDatabaseCallBack {
                override fun onCallBack(result: Any?) {
                    callOneButtonDialog("Done", "Ok")
                }
            })
        }
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save Instance State here
    }

    private fun onRestoreInstanceState(savedInstanceState: Bundle) { // Restore Instance State here
    }

    companion object {
        private const val TAG = "AddFragment"
        fun newInstance(): AddFragment {
            val fragment = AddFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}