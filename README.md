# FlowPractice 协程的Flow实战



![img.png](pic/flow_samples.png)

![img.png](pic/flow_page_struction.png)

- Download 文件下载
> Flow 主要用于 `异步返回多个值`，文件下载就是Flow 最经典的一个应用场景
>

![img.png](pic/flow_download.jpeg)



- Retrofit ：网络数据

- Room ：Jetpack 本地数据库

- stateFlow & SharedFlow ：热流
> FLOW 是冷流
> 
> 什么是冷流？
> 
> 简单来说，就是如果Flow 有了订阅者Collector 以后，发射出来的值才会实实在在存在于内存中。
> 这跟懒加载的概念很像。
> 
> 与之相对的是 `热流`，`StateFlow` 和 `SharedFlow` 是热流，在垃圾回收之前，都是存在内存之中的，并且处于活跃状态。
> 

> **stateFlow** : 是一个状态器式 `可观察数据流`，可以向其收集器发出当前状态更新和新状态更新。
> 还可通过其 value属性读取当前状态值。
> 

### Flow 与 jetpack paging3

![](pic/flow_paging3_mulu.png)

- 接口示例
> 
> https://www.kuaikanmanhua.com/v1/search/topic?q=任翔&f=3&size=18
> 
> http://m.kuaikanmanhua.com/search/mini/hot_word?&page=1&size=10
> 
> http://m.kuaikanmanhua.com/search/mini/hot_word?&page=2&size=10
> 
> http://m.kuaikanmanhua.com/search/mini/hot_word?&page=3&size=10
> 
> http://m.kuaikanmanhua.com/search/mini/hot_word?&page=4&size=10
> 

#### paging3
  
- 加载数据的流程
> 
![](pic/flow_paging3_start_con.png)

- 分页逻辑
>
![](pic/flow_page_more.png)

- 上游数据的缓存：屏幕旋转后，都会重新加载数据，解决此种情况

`.cachedIn(viewModelScope)`

> viewmodel, 放到属性上面去保存
> 
> 优化后，同时不会有内存泄漏的风险
> 
> **优化前：**
> 
```kotlin
fun loadMovie() : Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGING_PAGE_SIZE,
                // 滑动到 item位置加载更多
                prefetchDistance = 1,
                initialLoadSize= PAGING_INITIAL_PAGE_SIZE
            ),
            pagingSourceFactory = {MoviePagingSource()}
        ).flow
    }

```


> > **优化后：**
```kotlin
 private val movies by lazy {
        Pager(
            config = PagingConfig(
                pageSize = PAGING_PAGE_SIZE,
                // 滑动到 item位置加载更多
                prefetchDistance = 1,
                initialLoadSize= PAGING_INITIAL_PAGE_SIZE
            ),
            pagingSourceFactory = {MoviePagingSource()}
        ).flow.cachedIn(viewModelScope)
    }
    
    fun loadMovie() : Flow<PagingData<Movie>> = movies

```



#### 综合以供调试的Api接口

```html
https://www.kuaikanmanhua.com/v2/pweb/daily/topics?pos=6  // 获取每日更新  pos 星期几 0~6
https://www.kuaikanmanhua.com/v2/pweb/ugc/rec_topics // 原创
https://www.kuaikanmanhua.com/v2/pweb/ugc/topics // 最新上架

 http://m.kuaikanmanhua.com/v2/mweb/ugc/popularity_rank/topics?diffServerTimestamp=1615824259661&tag_id=20  // 投稿人气

// 获取分类数据
https://www.kuaikanmanhua.com/v1/search/by_tag?since=0&count=24&f=3&tag=63&sort=1&query_category={"update_status":1}
// update_status  书本状态        0 全部   1连载中 2完结
// count 返回数据数量
// sort 排序 1 推荐 2 最火热 3 新上架
// tag 分类  0 全部 20 恋爱 83 纯爱 47 校园  82 强剧情 46 古风 22 奇幻 67 热血 57 日漫 60 韩漫 77 大女主 40 完结 80 穿越 62 萌系 81 恐怖 65 悬疑 63 玄幻 71 搞笑 48 都市 54 正能量 52 总裁 76 投稿

https://www.kuaikanmanhua.com/v1/graph/pc/feeds/getRecommendFeed?uid=0&webTokenId=1615007958330_FFwnyURnzD0rgO2&since=0&limit=20  //世界
https://www.kuaikanmanhua.com/v2/pweb/home // 获取首页数据
https://www.kuaikanmanhua.com/v2/pweb/rank_type_list // 获取分类数据
https://www.kuaikanmanhua.com/v2/pweb/rank/topics?rank_id=5 获取榜单信息
https://www.kuaikanmanhua.com/v2/pweb/all_rank/topics // 获取全部榜单数据
https://www.kuaikanmanhua.com//v2/pweb/rank_type_list // 获取榜单列表
https://www.kuaikanmanhua.com/v1/search/topic?q=任翔&f=3&size=18 // 获取搜索结果
https://www.kuaikanmanhua.com/v1/search/suggestion_topic_author?q=元尊&type=2&f=2 // 获取模糊搜索
https://kuaikanmanhua.com//v1/search/by_tag?since=0&count=24&f=3&tag=63&sort=1&query_category={"update_status":1}  //  分类搜索
https://m.kuaikanmanhua.com/search/mini/topic/multi_filter?page=1&size=6&tag_id=63&gender=0&sort=1&fav_filter=0&update_status=1   // 分类获取数据
https://www.kuaikanmanhua.com/v2/pweb/topic/9265 // 获取漫画的详情 id
https://www.kuaikanmanhua.com//v2/pweb/comic/353105 // 获取漫画章节详情· id
https://m.kuaikanmanhua.com/mini/v1/comic/mkuaikan/rank_list?limit=6&since=2&need_ranks=0&rank_id=5 移动端排行
https://m.kuaikanmanhua.com/v2/mweb/comic/65709 移动端获取章节
http://m.kuaikanmanhua.com/search/mini/hot_word?&page=1&size=10 // 获取搜索热词
http://m.kuaikanmanhua.com/search/mini/topic/title_and_author?page=1&size=20&q=%E6%96%97%E7%BD%97%E5%A4%A7%E9%99%86 移动端搜索



https://movie.douban.com/j/search_subjects?type=movie&tag=%E8%B1%86%E7%93%A3%E9%AB%98%E5%88%86&page_limit=50&page_start=0

https://movie.douban.com/j/search_subjects?type=movie&tag=%E8%B1%86%E7%93%A3%E9%AB%98%E5%88%86&start=0&count=8

https://movie.douban.com/j/search_subjects?type=movie&tag=%E8%B1%86%E7%93%A3%E9%AB%98%E5%88%86&start=0&count=8


https://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=20&page_start=100





```





