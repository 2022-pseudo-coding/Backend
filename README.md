# 说明
1. 本地开一个数据库，用户名：root，密码：123456，数据库名字：web3d
2. 运行数据库然后运行WEB3DApplication
# 代码说明
实体类：
1. Authority，两个权限：User和Admin
2. Problem，问题类，里面有所有的问题描述以及可以使用的指令，初始化见resources/ProblemConfig.xml
3. Instruction，指令，储存的是用户怎么安排这些指令，具体的执行逻辑在common/utils里
4. Solution，用户解决的指令序列

# todo
现在的/userDefine接口是用户新建问题
1. 用户新建自己项目
2. 用户新建自己模块
