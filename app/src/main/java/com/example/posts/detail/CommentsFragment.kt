package com.example.posts.detail

import android.annotation.SuppressLint
import android.content.Context
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
import com.example.posts.common.Router
import kotlinx.android.synthetic.main.fragment_detail.view.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class CommentsFragment : MvpAppCompatFragment(), CommentsView {

    var router: Router? = null

    @InjectPresenter
    lateinit var mPresenter : CommentsPresenter

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
        router?.showAlbums(userId)
    }

    override fun showLoadError() {
        if (context != null){
            Toast.makeText(context, "An error occurred during networking" , Toast.LENGTH_LONG).show()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Router){
            router = context
        }else{
            throw Exception("${context::class.simpleName}  must implement Router")
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