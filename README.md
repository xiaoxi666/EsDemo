# ElasticSearch基本使用Demo

狂神说在b站的Elastic基础课程，讲得不错，跟着搭建了一下项目，记在这里。

本项目额外说明：
1. 使用了更新的Es版本：7.13.4。
   - 如果需要es、kinaba、head插件、ik插件等资源，可关注公众号 xiaoxi666，并回复es获取。注意这里上传的是Mac版本。
2. 针对遇到的一些问题，调整了一些细节，使得项目能够运行起来：
- 关于后端
    - Es的查询，把`termQuery`换成了`matchPhraseQuery`，方便查询中文。
    - 后端请求加上`@ResponseBody`注解，自动将接口响应解析为Json，这样页面就不会报错了。
    - 添加了junit5相关测试依赖，使得测试代码能够顺利运行。并且项目以父子模块的方式组织，初学者可参考。
- 关于前端
    - Springboot默认读取静态资源的路径是/static/**，因此index.html中只要写static的子目录路径即可。
    - jd的抓取需要cookie。因此实现了cookie的解析，使用者将jd站点的cookie内容粘到src/main/resources/cookie.txt文件中即可。
    - 前端页面经常变化，因此改了一些html Document节点的解析方式。

![Image](https://github.com/xiaoxi666/Image-blob/raw/main/gh_xiaoxi666.png)