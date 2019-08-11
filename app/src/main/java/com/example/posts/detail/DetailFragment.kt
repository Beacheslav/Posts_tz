package com.example.posts.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posts.CommentsAdapter
import com.example.posts.R
import com.example.posts.models.*
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment : Fragment(), DetailContract.View {

    lateinit var mPresenter: DetailContract.Presenter
    lateinit var mAdapter: CommentsAdapter
    lateinit var mPost : Post

    override fun  setPresenter(presenter: DetailContract.Presenter) {
        mPresenter = presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let { mPost = it?.getParcelable("post")!! }
    }

    @SuppressLint("WrongConstant")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mPresenter.start()
        val v = inflater.inflate(R.layout.fragment_detail, container, false)
        v.rv_comments.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        mAdapter = CommentsAdapter(null, null, context)
        v.rv_comments.adapter = mAdapter

        mPresenter.loadComments(mPost)
        mPresenter.loadAutor(mPost.userId)
        return v
    }


    override fun onResume() {
        super.onResume()
    }


    override fun showLoadError() {
        if (context != null){
            Toast.makeText(context, "An error occurred during networking" , Toast.LENGTH_LONG).show()
        }
    }


    override fun updateListUi(comments : ArrayList<Comment>?, post : Post?, autor: Autor?) {
        var listType : ArrayList<RowType>? = null
        if (comments!= null && post!= null){
            listType = comments as ArrayList<RowType>
            listType.add(0, post)
            listType.add(1, Category("Comments"))
        }
        if (autor!= null){
            mAdapter.autor = autor
        }
        mAdapter.listType = listType
        mAdapter.notifyDataSetChanged()
    }

    override fun isActive() : Boolean {
        return isAdded
    }

    override fun showProgressBar() {
    }

    override fun hideProgressBar() {
    }

    companion object {
        fun getInstance(post : Post) : DetailFragment{
            return DetailFragment().apply { arguments = Bundle().apply { putParcelable("post", post) } }
        }
    }
}