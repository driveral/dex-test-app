package com.sample.dextestapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sample.dextestapp.R
import com.sample.dextestapp.databinding.LoginSelectFragmentBinding

class LoginSelectFragment : Fragment() {

    companion object {
        fun newInstance() = LoginSelectFragment()
    }

    val viewModel: LoginSelectViewModel by viewModels()
    lateinit var binding: LoginSelectFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.login_select_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            loginButton.setOnClickListener{navigateToLogin()}
            registerButton.setOnClickListener{navigateToRegister()}
        }
    }

    private fun navigateToLogin(){
        val directions = LoginSelectFragmentDirections.openLoginFragment()
        findNavController().navigate(directions)
    }

    private fun navigateToRegister(){
        val directions = LoginSelectFragmentDirections.openRegisterFragment()
        findNavController().navigate(directions)
    }

}