package com.sample.dextestapp.ui.common

import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.sample.dextestapp.R

open class BaseFragment : Fragment(){

    fun openLoginScreen(){
        val navOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.enter_from_bottom)
            .setExitAnim(R.anim.exit_to_top)
            .setPopUpTo(R.id.splashFragment, true)
            .build()
        findNavController().navigate(R.id.loginFragment, null, navOptions)
    }
}