package com.vvitt.whole.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vvitt.flowpractice.R
import com.vvitt.flowpractice.databinding.FragmentCarBinding


/**
 * @author please call me police uncel
 * @since 2023/5/24
 * @email 110
 * @desciption car数据界面- 对协程综合实践
 * //https://v.api.aa1.cn/api/api-bz/temp.php?url=www.aa1.cn
 * //https://v.api.aa1.cn/api/api-bz/temp.php?url=http://101.35.190.152:8080/
 * //https://v.api.aa1.cn/api/api-bz/temp.php?url=https://m.kuaikanmanhua.com/
 **/
class CarFragment : Fragment() {

    private val mBinding : FragmentCarBinding by lazy {
        FragmentCarBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}