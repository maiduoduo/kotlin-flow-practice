package com.vvitt.flowpractice.common

/**
 * @ClassName AppCommon
 * @author please call me police uncel
 * @since 2023/5/24.
 * @email 110
 * @Version: V1.0.0
 * @desciption 全局数据
 **/
class AppCommon {

    private val num1 = 10
    private val str1= "1234"
    // kotlin中没有int，也没有Integer，只有Int，需要默认给 0
    private var num2 = 0
    //常用api
    //
    private val api1 = "https://www.kuaikanmanhua.com/v1/search/by_tag?since=6&count=8&f=3&tag=0&sort=1&query_category={%22update_status%22:1}"
    companion object {
        val num3 = 10
        val str1 = "str"
        var num4 = 11
        var num5 = 0 // 另外定义方法看前面
        var str2: String? = null // 另外定义方法看前面

        val PAGING_PAGE_SIZE = 8
        val PAGING_INITIAL_PAGE_SIZE = 16
    }

}