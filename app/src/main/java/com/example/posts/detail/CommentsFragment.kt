package com.example.posts.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posts.App
import com.example.posts.CommentsAdapter
import com.example.posts.R
import com.example.posts.info.InfoActivity
import com.example.posts.models.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import javax.inject.Inject

class CommentsFragment : Fragment(), CommentsContract.View {

    @Inject
    lateinit var mPresenter: CommentsPresenter
    lateinit var mAdapter: CommentsAdapter
    lateinit var mPost : Post



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let { mPost = it?.getParcelable("post")!! }
        App.presenterComponent.injectCommentsPresenter(this)
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


    override fun onResume() {
        super.onResume()
        mPresenter.mView = this
        mPresenter.loadComments(mPost)
        mPresenter.loadAutor(mPost.userId)
    }

    override fun onPause() {
        super.onPause()
        mPresenter.mView = null
    }

    override fun showAlbums(userId: Int) {
        if (context == null) return
        val intent = Intent (context, InfoActivity :: class.java)
        intent.putExtra("user_id", userId)
        Log.i("Comment.id : ", userId.toString())
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
        fun getInstance(post : Post) : CommentsFragment{
            return CommentsFragment().apply { arguments = Bundle().apply { putParcelable("post", post) } }
        }
    }
}