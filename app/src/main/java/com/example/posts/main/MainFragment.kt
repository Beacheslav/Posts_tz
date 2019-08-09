package com.example.posts.main

import android.annotation.SuppressLint
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
import com.example.posts.models.Post
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment(), MainContract.View{

    private lateinit var v: View
    lateinit var mPresenter: MainContract.Presenter
    lateinit var adapter: PostsAdapter


    override fun  setPresenter(presenter: MainContract.Presenter) {
        mPresenter = presenter //todo  проверка на нуль
    }


    @SuppressLint("WrongConstant")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        v = inflater.inflate(R.layout.fragment_main, container, false)
        v.rv_posts.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        adapter = PostsAdapter(null, context)
        v.rv_posts.adapter = adapter

        return v
    }


    override fun onResume() {
        super.onResume()
        mPresenter.start()
    }


    override fun showLoadError() {
        if (context != null){
            Toast.makeText(context, "An error occurred during networking" , Toast.LENGTH_LONG).show()
        }
    }

    override fun updateListUi(posts : ArrayList<Post>) {
        adapter.posts = posts
        adapter.notifyDataSetChanged()
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
        fun getInstance() : MainFragment{
            return MainFragment()
        }
    }
}