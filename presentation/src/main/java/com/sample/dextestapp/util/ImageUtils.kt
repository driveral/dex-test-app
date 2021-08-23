package com.sample.dextestapp.util

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.sample.dextestapp.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String) {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    Picasso.get()
        .load(url)
        .placeholder(circularProgressDrawable)
        .error(R.drawable.ic_outline_broken_image_24)
        .into(this, object : Callback {
            override fun onSuccess() {
                // Image loaded, do nothing
            }

            override fun onError(p0: Exception?) {
                setOnClickListener {
                    setOnClickListener(null)
                    loadImage(url)
                }
            }

        })

    // Breaks shared transition animations, sometimes they
    // work but other times the animation shows resizing
    // from the side of the final image.
//    load(url) {
//        allowHardware(false)
//        placeholder(circularProgressDrawable)
//        crossfade(true)
//        error(R.drawable.ic_outline_broken_image_24)
//        listener(
//            onError = { _, _ ->
//                setOnClickListener {
//                    setOnClickListener(null)
//                    loadImage(url, builder)
//                }
//            }
//        )
//        builder()
//    }
}