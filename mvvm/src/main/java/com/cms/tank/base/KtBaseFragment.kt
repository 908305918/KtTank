package com.cms.tank.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.kongzue.dialog.v3.WaitDialog

abstract class KtBaseFragment<VB : ViewBinding> : Fragment() {

    protected var mAttachActivity: Activity? = null
    protected lateinit var mBinding: VB

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        mAttachActivity = activity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = obtainViewBinding(inflater, container)
        initViews()
        return mBinding.root
    }


    abstract fun obtainViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB


    abstract fun initViews()


    protected fun showLoading(msg: String) {
        if (mAttachActivity is AppCompatActivity) {
            WaitDialog.show(mAttachActivity as AppCompatActivity, msg)
        }
    }

    protected fun dismissLoading() {
        WaitDialog.dismiss()
    }
}