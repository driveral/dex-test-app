package com.sample.domain

import java.io.Serializable

data class Post(
    val postId: Long,
    val userId: Long,
    val caption: String?,
    val imageUrl: String,
    val liked: Boolean
) : Serializable