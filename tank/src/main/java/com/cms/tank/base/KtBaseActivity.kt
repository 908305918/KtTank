package com.cms.tank.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.kongzue.dialog.v3.WaitDialog

abstract class KtBaseActivity<VB : ViewBinding> : AppCompatActivity() {

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

    protected fun showLoading(msg: String) {
        WaitDialog.show(this, msg)
    }

    protected fun dismissLoading() {
        WaitDialog.dismiss()
    }
}