# Json-Instant

### 各个json序列化Instant博文相关演示代码

每一家JSON框架对Instant的序列化处理不一样，所以导致兼容性会差一些。因为已经研究一晚上了，
我试验一下看看市面上各个JSON框架对于Instant的处理和兼容性是怎么样的，一方面是为了以后避坑另外是好奇好奇。

项目结构为
- Mian/子包略
    - controller
      - TestController.java (用来测试mvc参数绑定)
      - TestController-request.http (请求文件)
    - entity
      - TimeDTO.java (里面放一个测试专门用来传递的实体类)
    - jsonconfig 
      -InstantDeserialize.java (配置的json配置)
- test/子包略
    - FastJsonTests.java (里面放fastjson的序列化和反序列化代码)
    - JacksonTests.java (里面放jackson的序列化和反序列化代码)
    - MixTests.java (里面放fastjson和jackson混合序列反序列化代码)
    - SummaryTests.java (里面总结打印每个工具序列化Instant的格式)


关于本项目更多请看我的博客文章 [java8的Instant反序列化失败异常总结](https://www.someget.cn/java/2021/10/30/java-json01.html)