package com.example.posts.common

import android.os.Bundle
import android.view.View
import com.bluelinelabs.conductor.Controller
import moxy.MvpDelegate

abstract class MvpController : Controller {

    private var mMvpDelegate: MvpDelegate<MvpController>? = null

    private val mvpDelegate: MvpDelegate<MvpController>?
        get() {
            if (this.mMvpDelegate == null) {
                this.mMvpDelegate = MvpDelegate(this)
            }

            return this.mMvpDelegate
        }

    abstract fun inject()

    protected constructor() : super() {
        inject()
        this.mvpDelegate?.onCreate(Bundle())
    }

    protected constructor(args: Bundle) : super(args) {
        inject()
        this.mvpDelegate?.onCreate(args)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        this.mvpDelegate?.onAttach()
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        this.mvpDelegate?.onDetach()

    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        this.mvpDelegate?.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.mvpDelegate?.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        this.mvpDelegate?.onSaveInstanceState(outState)
        this.mvpDelegate?.onDetach()
    }
}