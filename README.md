# android_mvp_with_datepicker_netstate
mvp struct with datepicker
本框架采用了mvp框架，充分参考了谷歌的mvpdemo，
而且集成了，流行的rxjava-retrofit-okhtp网络访问框架，
为了兼容不同android系统的时间控件的差异，特此集成datepickerk框架。
特点：
adapter基类采用反射
数据库操作基类使用反射，后期改为greendao框架。
按照业务逻辑构建包文件

raindemo

--application

--baseparts

           --dao
           公共基类
           
--businessone(业务one)

             --bean(业务one对象)
             
             --model(业务子模型)
             
             --presenter(业务one主持人)
             
             --view(业务one视图)
             
                   --activity
                   --fragment
                   --adapter
                   
--http(网络公共基类)

--receiver(广播接收)

--utils(工具类)

--views(自定义view)

--MainActivity


具体介绍见：csdn
