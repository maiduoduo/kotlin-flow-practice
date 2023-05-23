package com.vvitt.flowpractice.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.vvitt.flowpractice.adapter.MusicAdapter
import com.vvitt.flowpractice.databinding.FragmentMusicNetBinding
import com.vvitt.flowpractice.model.Music
import com.vvitt.flowpractice.viewmodel.MusicViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect

/**
 * @author please call me police uncel
 * @since 2023/5/23
 * @email 110
 * @desciption Flow 与 retrofit 结合应用的实践
 **/
class MusicNetFragment : Fragment() {

    private val viewModel by viewModels<MusicViewModel>()
    private val musicList = mutableListOf<Music>()

    private val mBinding : FragmentMusicNetBinding by lazy {
        FragmentMusicNetBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return mBinding.root
    }

    private fun TextView.textWatcherFlow() : Flow<String> = callbackFlow {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
//                offer(s.toString())
                trySend(s.toString()).isSuccess
            }

        }
        addTextChangedListener(textWatcher)
        awaitClose { removeTextChangedListener(textWatcher) }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //获取输入的关键字
        lifecycleScope.launchWhenCreated {
            musicList.clear()
            mBinding.etSearch.textWatcherFlow().collect {
                Log.d("vvitt", "collect keywords: $it")
                //不希望 collect 嵌套，修改viewmodel 使用livedata
//                viewModel.searchMusic(it.toInt(),"json").collect {
//                }
//                if (it.isBlank() || it.toInt() == 0){
//                    viewModel.searchMusic(null,"json")
//                }else{
//                    viewModel.searchMusic(it.toInt(),"json")
//                }

                viewModel.searchMusic(null,"json")
            }
        }


        context?.let {
            val adapter = MusicAdapter(it)
            mBinding.rvMusic.adapter = adapter
            viewModel.musics.observe(viewLifecycleOwner,{musics->
                if (musicList.size > 50)musicList.clear()
                musicList.add(musics)
                adapter.setData(musicList)
                mBinding.rvMusic.scrollToPosition(0)
            })
        }



    }



}

//private fun <T> Lazy<T>.searchMusic() {
//
//}
