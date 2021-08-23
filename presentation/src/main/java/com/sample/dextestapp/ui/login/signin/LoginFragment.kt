package com.sample.dextestapp.ui.login.signin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sample.dextestapp.R
import com.sample.dextestapp.databinding.LoginFragmentBinding
import com.sample.dextestapp.ui.common.BaseFragment
import com.sample.dextestapp.util.toLocalizedMessage
import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "LoginFragment"

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    lateinit var binding: LoginFragmentBinding

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenToImeActions()
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.loginSuccessFulEvent.observe(viewLifecycleOwner) {
            onLoginSuccessFul()
        }
        viewModel.loginErrorMessage.observe(viewLifecycleOwner) { error ->
            onLoginFailed(error.toLocalizedMessage(requireContext()))
        }
    }

    private fun listenToImeActions(){
        binding.inputPassword.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                binding.loginButton.performClick()
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun onLoginSuccessFul() {
        Log.d(TAG, "Login successful")

        val openMainContent = LoginFragmentDirections.openMainContent()
        findNavController().navigate(openMainContent)
    }

    private fun onLoginFailed(message: String) {
        Log.d(TAG, "Login failed: $message")

        Snackbar.make(binding.snackbarTarget, message, Snackbar.LENGTH_SHORT).show()
    }

}
