package com.sample.dextestapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.dextestapp.R
import com.sample.dextestapp.databinding.PostListItemBinding
import com.sample.domain.Post
import com.squareup.picasso.Picasso

private const val TAG = "PostAdapter"

val POST_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem.postId == newItem.postId

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem == newItem
}

class PostAdapter(
    val itemClicked: (PostListItemBinding, Post) -> Unit
) : ListAdapter<Post, PostViewHolder>(POST_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding: PostListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.post_list_item,
            parent,
            false
        )

        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.onBind(currentList[position])
        holder.binding.root.setOnClickListener {
            itemClicked(
                holder.binding,
                currentList[position]
            )
        }
    }

}

class PostViewHolder(
    val binding: PostListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(post: Post) {
        //Set transition names for shared element transition
        binding.image.transitionName = "image_${post.postId}"
        binding.caption.transitionName = "caption_${post.postId}"

        binding.post = post
        Picasso.get()
            .load(post.imageUrl)
            .error(R.drawable.ic_outline_broken_image_24)
            .into(binding.image)
    }
}