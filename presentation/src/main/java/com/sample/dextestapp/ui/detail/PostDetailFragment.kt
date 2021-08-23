package com.sample.dextestapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.sample.dextestapp.R
import com.sample.dextestapp.databinding.PostDetailFragmentBinding
import com.sample.dextestapp.ui.common.BaseFragment
import com.sample.dextestapp.util.loadImage

private const val TAG = "PostDetailFragment"

class PostDetailFragment : BaseFragment() {

    companion object {
        fun newInstance() = PostDetailFragment()
    }

    private val args: PostDetailFragmentArgs by navArgs()
    private val viewModel: PostDetailViewModel by viewModels()
    private var _binding: PostDetailFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.post_detail_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val post = args.post

        // Set transition names for shared element transition
        binding.image.transitionName = "image_${post.postId}"
        binding.caption.transitionName = "caption_${post.postId}"

        binding.post = post
        binding.image.loadImage(post.imageUrl)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}