package com.developmentavk.avk_app_kotlin.presentation.goods

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("IntToString")
    fun setTextInt(textView: TextView, elect: Int){
    textView.text = elect.toString()
    }