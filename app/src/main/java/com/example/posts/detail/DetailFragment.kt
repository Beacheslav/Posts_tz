package com.example.posts.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posts.CommentsAdapter
import com.example.posts.R
import com.example.posts.models.Autor
import com.example.posts.models.Comment
import com.example.posts.models.Post
import com.example.posts.info.InfoActivity
import kotlinx.android.synthetic.main.fragment_detail.view.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class DetailFragment : MvpAppCompatFragment(), DetailView {

    @InjectPresenter
    lateinit var mPresenter : DetailPresenter

    lateinit var mAdapter: CommentsAdapter
    lateinit var mPost : Post



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let { mPost = it?.getParcelable("post")!! }
    }

    @SuppressLint("WrongConstant")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_detail, container, false)
        v.rv_comments.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        mAdapter = CommentsAdapter(null, null, mPost){
            mPresenter.itemClick(it)
        }
        v.rv_comments.adapter = mAdapter

        return v
    }

    override fun loadScreen() {
        mPresenter.commentsRepo.create()
        mPresenter.loadComments(mPost)
        mPresenter.loadAutor(mPost.userId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.commentsRepo.destroy()
    }

    override fun showAlbums(userId: Int) {
        if (context == null) return
        val intent = Intent (context, InfoActivity :: class.java)
        intent.putExtra("user_id", userId)
        context!!.startActivity(intent)
    }

    override fun showLoadError() {
        if (context != null){
            Toast.makeText(context, "An error occurred during networking" , Toast.LENGTH_LONG).show()
        }
    }


    override fun updateListUi(listType : ArrayList<Comment>?, autor: Autor?) {
        if (autor!= null){
            mAdapter.autor = autor
        }
        mAdapter.comments = listType
        mAdapter.notifyDataSetChanged()
    }


    companion object {
        fun getInstance(post : Post) : DetailFragment{
            return DetailFragment().apply { arguments = Bundle().apply { putParcelable("post", post) } }
        }
    }
}