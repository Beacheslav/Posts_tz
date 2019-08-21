package com.example.posts.info

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posts.ApiSomaku
import com.example.posts.App
import com.example.posts.InfoAdapter
import com.example.posts.R
import com.example.posts.dagger.DaggerPresenterComponent
import com.example.posts.models.Autor
import com.example.posts.repo.InfoRepoImplemented
import kotlinx.android.synthetic.main.fragment_info.view.*
import javax.inject.Inject

class InfoFragment : Fragment(), InfoContract.View {

    @Inject
    lateinit var mPresenter: InfoPresenter
    lateinit var mAdapter: InfoAdapter
    private var mUserId: Int? = null


    override fun  setPresenter(presenter: InfoContract.Presenter) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let { mUserId = it?.getInt("user_id")!! }
        //mPresenter = InfoPresenter(InfoRepoImplemented(ApiSomaku.create()))
        App.presenterComponent.injectInfoPresenter(this)
    }

    @SuppressLint("WrongConstant")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_info, container, false)
        v.rv_info.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        mAdapter = InfoAdapter()
        v.rv_info.adapter = mAdapter

        return v
    }

    override fun onResume() {
        super.onResume()
        mPresenter.mView = this
        mPresenter.loadAutor(mUserId)
    }

    override fun onPause() {
        super.onPause()
        mPresenter.mView = null
    }

    override fun showLoadError() {
        if (context != null){
            Toast.makeText(context, "An error occurred during networking" , Toast.LENGTH_LONG).show()
        }
    }

    override fun updatePhotoCount(count: Int, id: Int) {
        mAdapter.notifyItemChanged(id%10 + 1, count)               //FIXME: КОСТЫЛЬ
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

    override fun updateUi() {
    }

    override fun isActive() : Boolean {
        return isAdded
    }

    override fun showProgressBar() {
    }

    override fun hideProgressBar() {
    }

    companion object {
        fun getInstance(userId : Int) : InfoFragment{
            return InfoFragment().apply { arguments = Bundle().apply { putInt("user_id", userId) } }
        }
    }
}