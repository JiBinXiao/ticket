开发文档地址:http://localhost:22224/ticketApi/doc.html<br>

技术概述：<br>
数据库：mysql 5.6+ <br>
springboot 2.1.7  作为底层<br>
druid+mybatis+redis+shiro+swagger2<br>
利用Aspect 做访问日志<br>
接口加解密 可配置<br>
图像识别：百度智能云 https://cloud.baidu.com/<br>


2019.08.07
springboot+mybaits+shiro+redis 环境搭建<br>
集成 druid 数据库连接池<br>
实现 AOP切面访问日志<br>
实现 接口加解密<br>
一些基础工具类和包的创建<br>


2019.08.08<br>
服务器jdk8,mysql,nginx环境搭建<br>
数据库表建立<br>
门票分类，门票查询基础功能实现<br>

2019.08.09<br>
集成swagger2在线生成api接口文档<br>4
在服务器上部署应用<br>
一些基本功能的开发<br>

2019.08.10<br>
静态页面的部署<小灰灰同学><br>
基本功能的开发<br>

2019.08.11
登录逻辑上线，初识图片识别
初版动态页面上线<小灰灰完成><br>

修改后端bug:<br>
1.查询门票详情  /ticket/queryDetailById 新增是否关注参数<br>
2.查询我的关注列表  /ticket/queryMyAdd 更改为查询我发布的门票列表<br>
3.查询我发布的门票列表 /ticket/queryDetailById 新增是否发布的状态字段<br>
4.修改门票信息 ticket/update bug修复<br>
5.取消关注 和关注门票 接口返回码 bug修复<br>


2019.08.13<br>
生成五月天歌名词典，注册用户随机取出一个作为用户名<br>
新增留言功能（发送邮件到我的邮箱）<br>
》门票详情新增关注用户数量（未完成）<br>
用户头像，选用保密，男，女三张图片<br>
确认看台和内场位置字段<br>
》检查前端逻辑<br>

2019.08.14<br>
和小灰灰一起改bug<br>
网站备案真是一件麻烦事儿呀<br>
（前端）新增歌单，座位图，收钱码等图片<br>
（前端）新增友情提示内容<br>

2019.08.14<br>
tess4j 图像识别 速度慢，准确率低，手动训练麻烦
接入百度图片识别技术

