package com.sample.dextestapp.ui.login.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sample.dextestapp.R
import com.sample.dextestapp.databinding.RegisterFragmentBinding
import com.sample.dextestapp.util.toLocalizedMessage
import com.sample.domain.ErrorEntity
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "RegisterFragment"

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private val viewModel: RegisterViewModel by viewModels()
    private var _binding: RegisterFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.register_fragment, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenToImeActions()

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.loginSuccessFulEvent.observe(viewLifecycleOwner) { onRegisterSuccessful() }
        viewModel.loginErrorMessage.observe(viewLifecycleOwner) { onRegisterFailed(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun listenToImeActions() {
        binding.inputPasswordRepeat.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                binding.loginButton.performClick()
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun onRegisterSuccessful() {
        findNavController().navigate(RegisterFragmentDirections.openLoginFragment())
    }

    private fun onRegisterFailed(error: ErrorEntity) {
        val message = error.toLocalizedMessage(requireContext())

        Log.d(TAG, "Login failed: $message")
        Snackbar.make(binding.snackbarTarget, message, Snackbar.LENGTH_SHORT).show()
    }
}