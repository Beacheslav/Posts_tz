package com.example.posts.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posts.PostsAdapter
import com.example.posts.R
import com.example.posts.detail.CommentsActivity
import com.example.posts.models.Post
import kotlinx.android.synthetic.main.fragment_main.view.*

class PostsFragment : Fragment(), PostsContract.View{


    override fun setPresenter(presenter: PostsContract.Presenter) {

    }

    lateinit var mPresenter: PostsPresenter
    lateinit var mAdapter: PostsAdapter



    @SuppressLint("WrongConstant")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_main, container, false)
        v.rv_posts.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        mAdapter = PostsAdapter(null){
            mPresenter.itemClick(it)
        }
        v.rv_posts.adapter = mAdapter


        return v
    }

    override fun showPost(item: Post) {
        val intent = Intent (context, CommentsActivity :: class.java)
        intent.putExtra("post", item)
        context?.startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        mPresenter.mView = null
    }

    override fun onResume() {
        super.onResume()
        mPresenter.mView = this
        mPresenter.loadList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = PostsPresenter()
    }

    override fun showLoadError() {
        if (context != null){
            Toast.makeText(context, "An error occurred during networking" , Toast.LENGTH_LONG).show()
        }
    }

    override fun updateListUi(posts : ArrayList<Post>) {
        mAdapter.posts = posts
        mAdapter.notifyDataSetChanged()
    }

    override fun updateUi() {
//        v.tv.text = "Default"
    }

    override fun isActive() : Boolean {
        return isAdded
    }

    override fun showProgressBar() {
    }

    override fun hideProgressBar() {
    }

    companion object {
        fun getInstance() : PostsFragment{
            return PostsFragment()
        }
    }
}