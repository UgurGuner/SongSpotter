package com.eugurguner.songspotter.presentation.core.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.eugurguner.songspotter.databinding.RightArrowViewBinding

class RightArrowView(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {

    init {
        RightArrowViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

}