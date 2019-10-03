package com.example.posts.posts

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
import com.example.posts.PostsAdapter
import com.example.posts.R
import com.example.posts.common.MvpController
import com.example.posts.detail.CommentsController
import com.example.posts.models.Post
import kotlinx.android.synthetic.main.fragment_posts.view.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class PostsController : MvpController(), PostsView{

    override fun inject() {
        App.presenterComponent.inject(this)
    }

    @Inject
    @InjectPresenter
    lateinit var mPresenter: PostsPresenter

    @ProvidePresenter
    fun providePresenter() = mPresenter

    lateinit var mAdapter: PostsAdapter

    @SuppressLint("WrongConstant")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val v = inflater.inflate(R.layout.fragment_posts, container, false)
        v.rv_posts.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false)
        mAdapter = PostsAdapter(null){
            mPresenter.itemClick(it)
        }
        v.rv_posts.adapter = mAdapter
        return v
    }

    override fun showPost(item: Post) {
        router.pushController(RouterTransaction.with(CommentsController(item)))
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.postRepo.destroy()
    }

    override fun showLoadError() {
        Toast.makeText(applicationContext, "An error occurred during networking", Toast.LENGTH_LONG).show()
    }

    override fun updateListUi(posts : ArrayList<Post>) {
        mAdapter.posts = posts
        mAdapter.notifyDataSetChanged()
    }

    override fun showMessage(message: Int) {}
}