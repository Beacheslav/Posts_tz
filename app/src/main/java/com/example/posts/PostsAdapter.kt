package com.example.posts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.posts.models.Post
import kotlinx.android.synthetic.main.item_post.view.*

class PostsAdapter (var posts : ArrayList<Post>?, val context : Context?) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(v, context)
    }

    override fun getItemCount(): Int {
        return if (posts != null) {
            posts!!.size
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: PostsAdapter.ViewHolder, position: Int) {
        if (posts != null) {
            holder.bindPost(posts!![position])
        }
    }

    class ViewHolder(itemView : View, val context : Context?) : RecyclerView.ViewHolder(itemView){

        fun bindPost(post : Post){
            itemView.tv_title.text = post.title
            itemView.tv_description.text = post.body

            itemView.setOnClickListener(View.OnClickListener { v->
                if (context != null){
                    Toast.makeText(context, "Click on id:" + post.id, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}