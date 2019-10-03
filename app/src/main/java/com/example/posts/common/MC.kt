package com.example.posts.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import moxy.MvpDelegate

abstract class MC : Controller {

    private var isStateSaved: Boolean = false
    private var mMvpDelegate: MvpDelegate<out MC>? = null

    private val mvpDelegate: MvpDelegate<*>?
        get() {
            if (this.mMvpDelegate == null) {
                this.mMvpDelegate = MvpDelegate(this)
            }

            return this.mMvpDelegate
        }

    protected constructor() : super() {
        this.mvpDelegate?.onCreate()
    }

    protected constructor(args: Bundle) : super(args) {
        this.mvpDelegate?.onCreate(args)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        this.mvpDelegate?.onCreate()
        this.isStateSaved = false
        return View(applicationContext)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        this.isStateSaved = false
        this.mvpDelegate?.onAttach()
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        this.mvpDelegate?.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this.activity!!.isFinishing) {
            this.mvpDelegate?.onDestroy()
        } else if (this.isStateSaved) {
            this.isStateSaved = false
        } else {
            var anyParentIsRemoving = false

            var parent = this.parentController
            while (!anyParentIsRemoving && parent != null) {
                anyParentIsRemoving = parent!!.isDestroyed
                parent = parent!!.parentController
            }

            if (this.isDestroyed || anyParentIsRemoving) {
                this.mvpDelegate?.onDestroy()
            }

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        this.isStateSaved = true
        this.mvpDelegate?.onSaveInstanceState(outState)
        this.mvpDelegate?.onDetach()
    }
}