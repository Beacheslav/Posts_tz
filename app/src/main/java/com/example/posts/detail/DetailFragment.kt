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

    override fun  setPresenter(presenter: DetailContract.Presenter) {
        mPresenter = presenter //todo  проверка на нуль
    }


    @SuppressLint("WrongConstant")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_detail, container, false)
        v.rv_comments.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        mAdapter = CommentsAdapter(null, null, context)
        v.rv_comments.adapter = mAdapter
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


    override fun updateListUi(comments : ArrayList<Comment>?, post : Post?, autor: Autor?) {
        var listType = ArrayList<RowType>()
        if (comments!= null){
            listType = comments as ArrayList<RowType>
        }
        if (post!= null){
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
        fun getInstance() : DetailFragment{
            return DetailFragment()
        }
    }
}