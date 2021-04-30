package com.sample.dextestapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.sample.dextestapp.R
import com.sample.dextestapp.databinding.MainFragmentBinding
import com.sample.dextestapp.databinding.PostListItemBinding
import com.sample.dextestapp.ui.common.BaseFragment
import com.sample.domain.Post
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainFragment"

@AndroidEntryPoint
class MainFragment : BaseFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    lateinit var binding: MainFragmentBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PostAdapter { binding, post ->
            // User selected a post, navigate to the detail.
            openShowDetail(post, binding)
        }

        binding.postRecycler.apply {
            this.adapter = adapter
            // Shared element transition
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }

        binding.refreshLayout.setOnRefreshListener { viewModel.loadPosts() }

        viewModel.postList.observe(viewLifecycleOwner) { adapter.submitList(it) }
        viewModel.loading.observe(viewLifecycleOwner) { binding.refreshLayout.isRefreshing = it }
    }

    private fun openShowDetail(post: Post, binding: PostListItemBinding) {
        val directions = MainFragmentDirections.openPostDetail(post)
        // Shared element transition to the details screen.

        val extras = FragmentNavigatorExtras(
            binding.image to "image_${post.postId}",
            binding.caption to "caption_${post.postId}",
        )
        findNavController().navigate(directions, extras)
    }

}