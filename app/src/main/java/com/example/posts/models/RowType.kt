package com.example.posts.models

interface RowType {
    companion object {
        val POST_TYPE: Int = 0
        val CATEGORY_TYPE: Int = 1
        val COMMENT_TYPE: Int = 2
    }
}