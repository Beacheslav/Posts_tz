package com.example.posts.albums

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posts.InfoAdapter
import com.example.posts.R
import com.example.posts.models.Autor
import kotlinx.android.synthetic.main.fragment_info.view.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class AlbumFragment : MvpAppCompatFragment(), AlbumView {

    @InjectPresenter
    lateinit var mPresenter : AlbumPresenter

    lateinit var mAdapter: InfoAdapter
    private var mUserId: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let { mUserId = it?.getInt("user_id")!! }
    }

    @SuppressLint("WrongConstant")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_info, container, false)
        v.rv_info.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        mAdapter = InfoAdapter()
        v.rv_info.adapter = mAdapter

        return v
    }

    override fun loadScreen() {
        mPresenter.infoRepo.create()
        mPresenter.loadAutor(mUserId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.infoRepo.destroy()
    }

    override fun showLoadError() {
        if (context != null){
            Toast.makeText(context, "An error occurred during networking" , Toast.LENGTH_LONG).show()
        }
    }

    override fun updatePhotoCount(count: Int, id: Int) {
        mAdapter.notifyItemChanged(id%10 + 1, count)
    }

    override fun showList(list: List<InfoAdapter.Info>?) {
        list?.let {
            mAdapter.setInfo(it)
            mAdapter.notifyDataSetChanged()
        }
    }

    override fun showAutor(mAutor: Autor?) {
        mAdapter.setAutor(mAutor)
    }


    companion object {
        fun getInstance(userId : Int) : AlbumFragment{
            return AlbumFragment().apply { arguments = Bundle().apply { putInt("user_id", userId) } }
        }
    }
}