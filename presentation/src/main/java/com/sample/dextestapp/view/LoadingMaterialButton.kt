package com.sample.dextestapp.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.sample.dextestapp.R

class LoadingMaterialButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val progressBar: CircularProgressIndicator
    private val materialButton: MaterialButton
    private var buttonText: CharSequence? = null

    init {
        val root = LayoutInflater.from(context).inflate(R.layout.loading_button, this, true)
        materialButton = root.findViewById(R.id.button)
        progressBar = root.findViewById(R.id.progress_circular)
        loadAttr(attrs, defStyleAttr)
    }

    private fun loadAttr(attrs: AttributeSet?, defStyleAttr: Int) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LoadingMaterialButton,
            defStyleAttr, 0
        ).apply {

            try {
                val text = getString(R.styleable.LoadingMaterialButton_text)
                val enabled = getBoolean(R.styleable.LoadingMaterialButton_enabled, true)
                val loading = getBoolean(R.styleable.LoadingMaterialButton_loading, false)

                isEnabled = enabled
                materialButton.isEnabled = enabled
                setText(text)
                setLoading(loading)
            } finally {
                recycle()
            }
        }
    }

    fun setLoading(loading: Boolean) {
        isClickable = !loading //Disable clickable when loading
        if (loading) {
            buttonText = materialButton.text
            materialButton.text = ""
            progressBar.visibility = View.VISIBLE
        } else {
            materialButton.text = buttonText
            progressBar.visibility = View.GONE
        }
    }

    fun setText(text: String?) {
        buttonText = text
        materialButton.text = text
    }

    override fun setOnClickListener(l: OnClickListener?) {
        materialButton.setOnClickListener(l)
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        materialButton.isEnabled = enabled
    }
}