package com.vvitt.flowpractice.ui.fragment

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.vvitt.flowpractice.databinding.FragmentMviBinding
import com.vvitt.flowpractice.mvi.adapter.GalleryAdapter
import com.vvitt.flowpractice.mvi.data.intent.GalleryIntent
import com.vvitt.flowpractice.mvi.data.state.GalleryState
import com.vvitt.flowpractice.mvi.data.viewmodel.GalleryViewModel
import com.vvitt.flowpractice.mvi.data.viewmodel.ViewModelFactory
import com.vvitt.flowpractice.mvi.utils.DialogLoadingUtils
import com.vvitt.flowpractice.mvi.utils.ToastUtil
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 * @author please call me police uncel
 * @since 2023/6/9
 * @email 110
 * @desciption mvi 示例
 *
 * MVI流程：
 *  页面UI(点击事件发送意图)
 *  → ViewModel收集意图(确定内容)
 *  → ViewModel更新状态(修改_state)
 *  → 页面观察ViewModel状态(收集state，执行相关的UI)
 *
 **/
class MVIFragment : Fragment() {

    private val spanCount = 1 //每行列数为1
    private val spanCountMore = 2 //每行列数为3
    private val spanSize = 1 //每列之间的间距

//    private val viewmodel by viewModels<GalleryViewModel>()
//    private val mBinding : FragmentMviBinding by lazy {
//        FragmentMviBinding.inflate(layoutInflater)
//    }
    private lateinit var viewModel: GalleryViewModel
    private lateinit var mBinding : FragmentMviBinding
    private val adapter by lazy {
        context?.let {
            GalleryAdapter(it,arrayListOf())
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //使用viewBinding
        mBinding = FragmentMviBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //绑定viewmodel
        viewModel = ViewModelProvider(this, ViewModelFactory())[GalleryViewModel::class.java]

        //RecyclerView配置
        //避免RecyclerView重新计算大小
        val layoutManager = StaggeredGridLayoutManager(spanCountMore, StaggeredGridLayoutManager.VERTICAL)
        //避免滑动加载时跳动
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        mBinding.rvGallery.layoutManager = layoutManager
        mBinding.rvGallery.addItemDecoration(SpaceItemDecoration(spanSize)) //设置间距
        mBinding.rvGallery.setHasFixedSize(true)
        mBinding.rvGallery.adapter = adapter

        mBinding.btnGetGallery.setOnClickListener{
            lifecycleScope.launchWhenCreated {
                //发送意图
                //发送的意图被 GalleryViewModel 中 mainIntentChannel 收集到，
                // 然后执行网络请求操作，此时意图的状态为Loading。
                viewModel.mainIntentChannel.send(GalleryIntent.GetGallery)

            }
        }

        //观察ViewModel
        observeViewModel()
    }


    /**
     * @desciption 监听viewmodel
     * @since 2023/6/9
     **/
    private fun observeViewModel() {
        lifecycleScope.launch {
            //状态收集
            viewModel.stateflow.collect {
                when(it){
                    is GalleryState.Idle -> {}
                    is GalleryState.Loading ->{
                        context?.let { context ->
                            DialogLoadingUtils.showLoading(context, "请稍后")
                        }
                        mBinding.btnGetGallery.isVisible = false

                    }
                    is GalleryState.Gallerys -> {//数据获取成功
                        DialogLoadingUtils.cancel()
                        mBinding.btnGetGallery.isVisible = false
                        mBinding.rvGallery.isVisible = true
                        it.mviGalleryModel?.let { galleryData ->
                            adapter?.setData(galleryData.res.vertical)
                        }
                    }
                    is GalleryState.Error -> {
                        DialogLoadingUtils.cancel()
                        mBinding.btnGetGallery.isVisible = true
                        mBinding.rvGallery.isVisible = false
                        Log.d("vvitt", "=====> observeViewModel:  ${it.error}")
                        ToastUtil.shortShow("${it.error}")
                    }
                }
            }
        }

    }


    inner class SpaceItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() { //切换之后重新设置间距
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.left = space
            outRect.right = space
            outRect.top = space
            outRect.bottom = space
        }
    }


}