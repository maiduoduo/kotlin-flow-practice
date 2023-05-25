package com.vvitt.flowpractice.common

/**
 * @ClassName AppCommon
 * @author please call me police uncel
 * @since 2023/5/24.
 * @email 110
 * @Version: V1.0.0
 * @desciption 全局数据
 **/
const val PAGING_PAGE_SIZE = 8
const val PAGING_INITIAL_PAGE_SIZE = 16
const val BASE_URL = "http://m.kuaikanmanhua.com/"
const val BASE_URL1 = "http://192.168.0.103:8080/dongnaoedu/"
//The name of the database file
const val DB_NAME = "vvitt_car_home.db"


object AppCommon {
    private val num1 = 10
    private val str1= "1234"
    // kotlin中没有int，也没有Integer，只有Int，需要默认给 0
    private var num2 = 0
    //常用api
    private val api1 = "https://www.kuaikanmanhua.com/v1/search/by_tag?since=6&count=8&f=3&tag=0&sort=1&query_category={%22update_status%22:1}"

}