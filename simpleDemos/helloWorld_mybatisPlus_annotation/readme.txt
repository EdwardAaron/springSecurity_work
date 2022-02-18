1 使用springSecurity配置权限管理
2 使用springSecurity注解，指定方法权限
3 是用mybatis-plus存储数据
4 数据库mysql
5 模板 thymleaf
6 测试地址
    需要admin权限 @Secured  http://localhost:8003/person/1
    不需要登录 http://localhost:8003/home
    不要角色或权限，但是要登录 http://localhost:8003/admin
    需要角色admin http://localhost:8003/admin/manage
7 登录用户
    lucia，密码 lucia 有admin 角色
    userA，密码 userA 没有任何权限
8 使用@ControllerAdvice简略处理异常
