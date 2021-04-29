package com.sample.dextestapp.view

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.core.widget.ImageViewCompat

class CustomImageFilterView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageFilterView(context, attrs, defStyleAttr) {

    fun setTint(color: Int){
        ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(color))
    }

    fun getTint(): Int = ImageViewCompat.getImageTintList(this)!!.defaultColor
}