# rpcDmeo

### 简介：rpc框架，是基于http协议实现的远程调用，这个框架目前只用来学习使用，不做任何商业用途，欢迎学习分布式的朋友能加入进来一起学习。

--
- 这只是1.0版本，还没有加入zk，实现分布式注册服务，在下一个版本里将会加入。
- 以后还是慢慢改变为netty来实现

--
#### 使用：
- 先将用gradle build -x test 将jar下包下载完整
- 在provider 里运行Launch，将服务注册并监听着
- 然后跑consumer里的UserController，来实现远程调用
