package com.example.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.posts.models.Post
import kotlinx.android.synthetic.main.item_post.view.*

class PostsAdapter(
    var posts: ArrayList<Post>?,
    val clickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return if (posts != null) {
            posts!!.size
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (posts != null) {
            holder.bindPost(posts!![position])
            holder.itemView.setOnClickListener {
                clickListener(position)
            }
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindPost(post : Post){
            itemView.tv_title.text = post.title
            itemView.tv_description.text = post.body
        }
    }
}