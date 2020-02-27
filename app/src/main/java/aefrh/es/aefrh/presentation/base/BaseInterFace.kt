package aefrh.es.aefrh.presentation.base

import androidx.databinding.ViewDataBinding

interface BaseInterFace {
    val layout: Int
    fun initUI(binding: ViewDataBinding?)
    fun onCreate()
}