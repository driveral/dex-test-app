package com.sample.dextestapp.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sample.dextestapp.R
import com.sample.dextestapp.databinding.SplashFragmentBinding
import com.sample.dextestapp.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private val viewModel: SplashViewModel by viewModels()
    private var _binding: SplashFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.splash_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToViewModelEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeToViewModelEvents() {
        viewModel.navigateToContentEvent.observe(viewLifecycleOwner) {
            playAnimation {
                findNavController().navigate(SplashFragmentDirections.openMainContent())
            }
        }
        viewModel.navigateToLoginEvent.observe(viewLifecycleOwner) {
            playAnimation {
                openLoginScreen()
            }
        }
    }

    private fun playAnimation(onAnimationCompleted: () -> Unit) {
        lifecycleScope.launch {

            // TODO temporal fix, it will skip the animation otherwise
            delay(500)

            binding.motionLayout.addTransitionListener(object : MotionLayout.TransitionListener {
                override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                    // Not used.
                }

                override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                    // Not used.
                }

                override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                    onAnimationCompleted()
                }

                override fun onTransitionTrigger(
                    p0: MotionLayout?,
                    p1: Int,
                    p2: Boolean,
                    p3: Float
                ) {
                    // Not used.
                }
            })

            binding.motionLayout.transitionToEnd()
        }
    }


}