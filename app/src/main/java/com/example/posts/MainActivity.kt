package com.example.posts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.posts.albums.AlbumFragment
import com.example.posts.common.Router
import com.example.posts.common.Screens
import com.example.posts.detail.CommentsFragment
import com.example.posts.models.Post
import com.example.posts.posts.PostsFragment

class MainActivity : AppCompatActivity(), Router {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        if (supportFragmentManager.backStackEntryCount == 0){
            showPosts()
        }
    }

    fun showPosts(){
        val fragment = PostsFragment.getInstance()
        fragment.retainInstance = true
        supportFragmentManager.beginTransaction()
            .addToBackStack(Screens.POSTS_SCREEN)
            .replace(
                R.id.contentFrame,
                fragment
            ).commit()
    }

    override fun showComments(post: Post) {
        val fragment = CommentsFragment.getInstance(post)
        fragment.retainInstance = true
        supportFragmentManager.beginTransaction()
            .addToBackStack(Screens.COMMENTS_SCREEN)
            .replace(
            R.id.contentFrame,
            fragment
        ).commit()
    }

    override fun showAlbums(userId: Int) {
        val fragment = AlbumFragment.getInstance(userId)
        fragment.retainInstance = true
        supportFragmentManager.beginTransaction()
            .addToBackStack(Screens.ALBUM_SCREEN)
            .replace(
            R.id.contentFrame,
            fragment
        ).commit()
    }
}