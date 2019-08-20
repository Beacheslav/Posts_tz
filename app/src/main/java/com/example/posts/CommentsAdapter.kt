package com.example.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.posts.models.*
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_comment.view.*
import kotlinx.android.synthetic.main.item_header.view.*
import kotlinx.android.synthetic.main.item_post.view.tv_description
import kotlinx.android.synthetic.main.item_post.view.tv_title

class CommentsAdapter(
    var comments: ArrayList<Comment>?, var autor: Autor?, var post: Post,
    val clickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val POST_TYPE = 0
    private val CATEGORY_TYPE = 1
    private val COMMENT_TYPE = 2

    override fun getItemViewType(position: Int): Int {

        when (position) {
            0 -> return POST_TYPE
            1 -> return CATEGORY_TYPE
            else -> return COMMENT_TYPE
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            POST_TYPE -> {
                val v = LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
                return PostHolder(v)
            }
            CATEGORY_TYPE -> {
                val v = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
                return CategoryHolder(v)
            }
            else -> {
                val v = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
                return CommentHolder(v)
            }
        }
    }

    override fun getItemCount(): Int {
        return 2 + (comments?.size ?: 0)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PostHolder -> {
                holder.bindPost(post, autor)
                holder.itemView.setOnClickListener {
                    clickListener(post.userId)
                }
            }
            is CategoryHolder -> holder.bindCategory()
            is CommentHolder -> holder.bindComment(comments?.get(position - 2) ?: return)
        }

    }


    class PostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindPost(post: Post, autor: Autor?) {
            itemView.tv_title.text = post.title
            itemView.tv_description.text = post.body.replace("\n", "\\n", false)
            if (autor != null) {
                val textAutor = "(" + autor.name + ", " + autor.username + ", " + autor.email + ")"
                itemView.tv_autor.text = textAutor
            }
        }
    }

    class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindCategory() {
            val text = "Comments"
            itemView.tv_category.text = text

        }
    }

    class CommentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindComment(comment: Comment) {
            itemView.tv_name.text = comment.name
            itemView.tv_email.text = comment.email
            itemView.tv_body.text = comment.body
        }
    }
}