package com.cms.tank.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected val mBinding: VB by lazy {
        obtainViewBinding()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(mBinding.root)
        initViews()
    }

    abstract fun obtainViewBinding(): VB

    abstract fun initViews()
}