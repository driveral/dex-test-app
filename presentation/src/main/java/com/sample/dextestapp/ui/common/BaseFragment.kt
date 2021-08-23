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
            .setPopEnterAnim(R.anim.enter_from_top)
            .setPopExitAnim(R.anim.exit_to_bottom)
            .setPopUpTo(R.id.splashFragment, true)
            .build()
        findNavController().navigate(R.id.login_graph, null, navOptions)
    }
}