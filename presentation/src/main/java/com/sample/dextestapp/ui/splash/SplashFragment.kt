package com.sample.dextestapp.ui.splash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.sample.dextestapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToViewModelEvents()

        viewModel.checkIfUserIsLoggedIn()
    }

    private fun subscribeToViewModelEvents() {
        viewModel.navigateToContentEvent.observe(viewLifecycleOwner) {
            navigateToDirections(
                SplashFragmentDirections.openMainContent()
            )
        }
        viewModel.navigateToLoginEvent.observe(viewLifecycleOwner) {
            navigateToDirections(
                SplashFragmentDirections.openLogin()
            )
        }
    }

    private fun navigateToDirections(directions: NavDirections) {
        lifecycleScope.launch {

            // TODO temporal fix, it will skip the animation otherwise
            delay(500)

            val motion = requireView().findViewById<MotionLayout>(R.id.motionLayout)
            motion.addTransitionListener(object : MotionLayout.TransitionListener {
                override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                    // Not used.
                }

                override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                    // Not used.
                }

                override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                    findNavController().navigate(directions)
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

            motion.transitionToEnd()
        }
    }


}