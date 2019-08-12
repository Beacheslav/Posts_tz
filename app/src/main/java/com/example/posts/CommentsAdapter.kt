package com.example.posts

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.posts.info.InfoActivity
import com.example.posts.models.*
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_comment.view.*
import kotlinx.android.synthetic.main.item_header.view.*
import kotlinx.android.synthetic.main.item_post.view.tv_description
import kotlinx.android.synthetic.main.item_post.view.tv_title

class CommentsAdapter (var listType : ArrayList<RowType>?, var autor: Autor?, val context : Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        if (listType == null) {
            return -1
        }
        return when (listType!![position]) {
            is Post -> RowType.POST_TYPE
            is Category -> RowType.CATEGORY_TYPE
            is Comment -> RowType.COMMENT_TYPE
            else -> -1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (listType != null) {
            when (viewType) {
                RowType.POST_TYPE -> {
                    val v = LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
                    return PostHolder(v, context, autor)
                }
                RowType.CATEGORY_TYPE -> {
                    val v = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
                    return CategoryHolder(v)
                }
                RowType.COMMENT_TYPE -> {
                    val v = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
                    return CommentHolder(v, context)
                }
            }
        }
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return CommentHolder(v, context)
    }

    override fun getItemCount(): Int {
        return if (listType != null) {
            listType!!.size
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (listType != null) {
            when(holder){
                is PostHolder -> (holder as PostHolder).bindPost(listType!![position] as Post)
                is CategoryHolder -> (holder as CategoryHolder).bindCategory(listType!![position] as Category)
                is CommentHolder -> (holder as CommentHolder).bindComment((listType!![position]) as Comment)
            }
        }
    }


    class PostHolder(itemView : View, val context : Context?, val autor: Autor?) : RecyclerView.ViewHolder(itemView){

        fun bindPost(post : Post){
            itemView.tv_title.text = post.title
            itemView.tv_description.text = post.body.replace("\n", "\\n", false)
            if (autor != null) {
                val textAutor = "(" + autor.name + ", " + autor.username + ", " + autor.email + ")"
                itemView.tv_autor.text = textAutor
            }
        }
    }

    class CategoryHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindCategory(category: Category){
            val text = category.category
            itemView.tv_category.text = text

        }
    }

    class CommentHolder(itemView : View, val context : Context?) : RecyclerView.ViewHolder(itemView){

        fun bindComment(comment : Comment){
            itemView.tv_name.text = comment.name
            itemView.tv_email.text = comment.email
            itemView.tv_body.text = comment.body

            itemView.setOnClickListener(View.OnClickListener { v->
                if (context != null){

                    val intent = Intent (context, InfoActivity :: class.java)
                    intent.putExtra("user_id", comment.id)
                    context.startActivity(intent)
                }
            })
        }
    }
}