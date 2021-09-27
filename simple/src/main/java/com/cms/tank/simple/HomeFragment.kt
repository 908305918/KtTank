package com.cms.tank.simple

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.cms.tank.property.argument
import com.cms.tank.property.viewBinding
import com.cms.tank.simple.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val mBinding by viewBinding(FragmentHomeBinding::bind)

    private var orderId: Int by argument()
    private var orderType: Int by argument(1)

    companion object {
        fun newInstance(orderId: Int, orderType: Int?) = HomeFragment().apply {
            this.orderId = orderId
            orderType?.also { this.orderType = it }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.mBinding.ff.text = orderId.toString()
    }
}