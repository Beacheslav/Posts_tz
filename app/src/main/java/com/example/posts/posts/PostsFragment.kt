package com.example.posts.posts

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posts.PostsAdapter
import com.example.posts.R
import com.example.posts.common.Router
import com.example.posts.models.Post
import kotlinx.android.synthetic.main.fragment_main.view.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import java.lang.Exception

class PostsFragment : MvpAppCompatFragment(), PostsView{

    var router: Router? = null

    @InjectPresenter
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
        router?.showComments(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.postRepo.destroy()
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Router){
            router = context
        }else{
            throw Exception("${context::class.simpleName}  must implement Router")
        }
    }

    override fun showMessage(message: Int) {}

    companion object {
        fun getInstance() : PostsFragment{
            return PostsFragment()
        }
    }
}