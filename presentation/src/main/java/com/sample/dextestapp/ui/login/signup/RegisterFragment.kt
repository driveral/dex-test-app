package com.sample.dextestapp.ui.login.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    lateinit var binding: RegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.register_fragment, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.loginSuccessFulEvent.observe(viewLifecycleOwner) { onRegisterSuccessful() }
        viewModel.loginErrorMessage.observe(viewLifecycleOwner) { onRegisterFailed(it) }
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