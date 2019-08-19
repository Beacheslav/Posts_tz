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

class CommentsAdapter (var comments : ArrayList<Comment>?, var autor: Autor?, var post: Post) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {

        when (position) {
            0 -> return RowType.POST_TYPE
            1 -> return RowType.CATEGORY_TYPE
            else -> return RowType.COMMENT_TYPE
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            RowType.POST_TYPE -> {
                val v = LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
                return PostHolder(v)
            }
            RowType.CATEGORY_TYPE -> {
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
         return  2 + (comments?.size ?: 0)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PostHolder -> holder.bindPost(post, autor)
            is CategoryHolder -> holder.bindCategory()
            is CommentHolder -> holder.bindComment(comments?.get(position - 2) ?: return)
        }

    }


    class PostHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindPost(post: Post, autor: Autor?){
            itemView.tv_title.text = post.title
            itemView.tv_description.text = post.body.replace("\n", "\\n", false)
            if (autor != null) {
                val textAutor = "(" + autor.name + ", " + autor.username + ", " + autor.email + ")"
                itemView.tv_autor.text = textAutor
            }
        }
    }

    class CategoryHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindCategory(){
            val text = "Comments"
            itemView.tv_category.text = text

        }
    }

    class CommentHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindComment(comment : Comment){
            itemView.tv_name.text = comment.name
            itemView.tv_email.text = comment.email
            itemView.tv_body.text = comment.body

//            itemView.setOnClickListener(View.OnClickListener { v->
//                if (context != null){
//
//                    val intent = Intent (context, InfoActivity :: class.java)
//                    intent.putExtra("user_id", comment.id)
//                    context.startActivity(intent)
//                }
//            })
        }
    }
}