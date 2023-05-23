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
