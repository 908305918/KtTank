package com.cms.tank.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kongzue.dialog.v3.WaitDialog

abstract class KtBaseFragment : Fragment() {

    protected fun showLoading(msg: String) {
        if (activity is AppCompatActivity) {
            WaitDialog.show(activity as AppCompatActivity, msg)
        }
    }

    protected fun dismissLoading() {
        WaitDialog.dismiss()
    }
}