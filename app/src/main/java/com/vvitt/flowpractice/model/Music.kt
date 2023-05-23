package com.vvitt.flowpractice.model

/**
 * @ClassName Music
 * @author please call me police uncel
 * @since 2023/5/23.
 * @email 110
 * @Version: V1.0.0
 * @desciption 网络请求的音乐数据类
 **/
//https://api.uomg.com/api/comments.163?format=text
//  名称   必填  类型	    说明
//	mid	   否	int	    网易云歌单ID
// 	format 否	string  选择输出格式[json|text]
//data class Music (val mid:Int , val format: String)

data class Music(val code:Int,val data: DataBean? = null)

data class DataBean(val name:String,val url:String,val picurl:String, val artistsname:String,
                    val avatarurl:String, val nickname:String, val content:String)


/*{
    "code": 1,
    "data": {
        "name": "可乐",
        "url": "http://music.163.com/song/media/outer/url?id=29759733.mp3",
        "picurl": "http://p3.music.126.net/qOfVT6izV4mBe4IyQn489Q==/18190320370401891.jpg",
        "artistsname": "赵紫骅",
        "avatarurl": "http://p3.music.126.net/0mmFwR_rvHrv1ofjE7e_sA==/109951163729562700.jpg",
        "nickname": "人間杜冷丁",
        "content": "分手一年半 最后一次在纠缠中吵架 星期一 你每天在好友验证里给我发早安 我想 你要是坚持到星期五我就原谅你 星期四早安断了 你有了女朋友 头像背景墙全是她 我们总是错过 分手也是 和好也是 原谅都是错过 四年结束了 祝福你"
    }
}*/
