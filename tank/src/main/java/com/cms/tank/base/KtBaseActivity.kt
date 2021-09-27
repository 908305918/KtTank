package com.cms.tank.base

import androidx.appcompat.app.AppCompatActivity
import com.kongzue.dialog.v3.WaitDialog

abstract class KtBaseActivity : AppCompatActivity() {

    protected fun showLoading(msg: String) {
        WaitDialog.show(this, msg)
    }

    protected fun dismissLoading() {
        WaitDialog.dismiss()
    }
}