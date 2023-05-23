package com.vvitt.flowpractice.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.vvitt.flowpractice.R
import com.vvitt.flowpractice.databinding.FragmentDownloadBinding
import com.vvitt.flowpractice.databinding.FragmentHomeBinding
import com.vvitt.flowpractice.download.DownloadManager
import com.vvitt.flowpractice.download.DownloadStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import java.io.File
import kotlin.random.Random


/**
 * @ClassName DownloadManager
 * @author please call me police uncel
 * @since 2023/5/22.
 * @email 110
 * @Version: V1.0.0
 * @desciption flow Download 文件下载 实践
 **/
class DownloadFragment : Fragment() {

    val URL = "http://p3.music.126.net/vtnI8JpimWnZSzkXdmIB3w==/109951168558210782.jpg"
    val URL1 = "https://imglf6.lf127.net/img/SS9DS2FDc2VlcnBaMVNoZ3Z1MGxaUEgvZ0tDVTJ1cjFBMUVSb2s3cUJtUkRBR1B1ZzJEUVpnPT0.jpg?imageView&thumbnail=3000y1487&type=jpg&quality=96&stripmeta=0&type=jpg"
    val URL2 = "http://p3.music.126.net/_v7ezNPQOEJ3aFir8MZmzQ==/19050138463061697.jpg"
    val URL3 = "http://p4.music.126.net/kVwk6b8Qdya8oDyGDcyAVA==/1364493930777368.jpg"
    val URL4 = "http://p4.music.126.net/li19i75jz6GGOT79IyAjYA==/109951165100592039.jpg"
    val URL5 = "http://p4.music.126.net/VDmN2dNpIFu4gTv4bZe6KQ==/109951166254691365.jpg"
    val URL51 = "http://p3.music.126.net/mglql8_Dlg8JQw00nI1S4g==/109951167350673553.jpg"
    //错误地址
    val URL6 = "http://p3.music.126.net/p-VC6Xf4EYOSAWiQdPQSsw==/109951163903681519999.jpg"
    private val mBinding : FragmentDownloadBinding by lazy {
        FragmentDownloadBinding.inflate(layoutInflater)
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
        val urlList = listOf<String>(URL,URL1,URL6,URL5,URL3,URL2,URL4,URL51)

        lifecycleScope.launchWhenCreated {
            context?.apply {

                //保存路径 写法1
                val file = File(getExternalFilesDir(null)?.path,"pic_vvitt_${System.currentTimeMillis()}.JPG")

                // 首先保存图片 写法2
               /* val storePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "FLOW_PIC";
                val appDir = File(storePath);
                if (!appDir.exists()) {
                    appDir.mkdir();
                }
                val fileName = "pic_vvitt_${System.currentTimeMillis()}.jpg";
                val file = File(appDir, fileName);*/

                // /storage/emulated/0/Android/data/com.vvitt.flowpractice/files/pic_vvitt.JPG
                // /storage/emulated/0/FLOW_PIC/pic_vvitt_1684771452003.jpg
                val urlIndex = Random.nextInt(8)
                DownloadManager.download(urlList[urlIndex],file).collect { status->
                    when(status){
                        is DownloadStatus.Progress ->{
                            mBinding.apply {
                                progressBar.progress = status.value
                                tvProgress.text = "${status.value}%"
                            }
                        }
                        is DownloadStatus.Done ->{
                            mBinding.apply {
                                progressBar.progress = 100
                                tvProgress.text = "100%"
                                Toast.makeText(context,"下载完成",Toast.LENGTH_SHORT).show()

                                //保存到系统图库
                                //把文件插入到系统图库
                                MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.name, null);
                                //保存图片后发送广播通知更新数据库
                                val uri = Uri.fromFile(file);
                                requireContext().sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));

                            }

                        }
                        is DownloadStatus.Error ->{
                            Log.e("vvitt", "下载错误: ${status.throwable}", )
                            Toast.makeText(context,"下载错误",Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Log.e("vvitt", "onActivityCreated: 下载失败")

                        }

                    }

                }
            }
        }
    }


}