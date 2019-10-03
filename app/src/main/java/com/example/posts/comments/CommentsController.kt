package com.example.posts.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluelinelabs.conductor.RouterTransaction
import com.example.posts.App
import com.example.posts.CommentsAdapter
import com.example.posts.R
import com.example.posts.albums.AlbumController
import com.example.posts.common.MvpController
import com.example.posts.models.Autor
import com.example.posts.models.Comment
import com.example.posts.models.Post
import kotlinx.android.synthetic.main.fragment_detail.view.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class CommentsController(bundle: Bundle) : MvpController(bundle), CommentsView {

    override fun inject() {
        App.presenterComponent.inject(this)
    }

    @Inject
    @InjectPresenter
    lateinit var mPresenter : CommentsPresenter

    @ProvidePresenter
    fun providePresenter() = mPresenter

    lateinit var mAdapter: CommentsAdapter

    constructor(parameter : Post) : this(Bundle().apply {
        putParcelable("post", parameter)
    })

    private val mPost by lazy {
        args.getParcelable<Post>("post")
    }

    @SuppressLint("WrongConstant")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup) : View {
        val v = inflater.inflate(R.layout.fragment_detail, container, false)
        v.rv_comments.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false)
        mAdapter = CommentsAdapter(null, null, mPost!!){
            mPresenter.itemClick(it)
        }
        v.rv_comments.adapter = mAdapter
        return v
    }

    override fun loadScreen() {
        mPresenter.commentsRepo.create()
        mPresenter.loadComments(mPost!!)
        mPresenter.loadAutor(mPost!!.userId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.commentsRepo.destroy()
    }

    override fun showAlbums(userId: Int) {
        router.pushController(RouterTransaction.with(AlbumController(userId)))
    }

    override fun showLoadError() {
        Toast.makeText(applicationContext, "An error occurred during networking", Toast.LENGTH_LONG).show()
    }

    override fun updateListUi(listType : ArrayList<Comment>?, autor: Autor?) {
        if (autor!= null){
            mAdapter.autor = autor
        }
        mAdapter.comments = listType
        mAdapter.notifyDataSetChanged()
    }
}