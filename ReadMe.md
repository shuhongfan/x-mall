# X-MALL电商平台

## 系统架构

后台： SpringBoot + Mybatis-plus + Mybatis + Hutool工具包 + lombok插件

前台：Vue + Vue Router + ELementUI + Axios 



## 系统简介

功能：首页推荐（默认根据用户买过的商品进行推荐，如果没买过则根据商品销量推荐）、轮播图管理、商品、订单、购物车、商品评论、商品评论点赞、商品收藏、商品点赞、订单支付、公告。

角色：管理员、商家、用户

后台菜单权限：基于RBAC权限模型（自创）



## 功能图示

![功能图示](https://www.hualigs.cn/image/60a082a23e82d.jpg)



## 运行环境

JDK：1.8

Node:  12.13.1

数据库：Mysql5.7 +

浏览器：Chrome

需要自己安装JDK、Node、Mysql、 IDEA 、Navicat等软件，下载地址：https://www.jianguoyun.com/p/Dc5ht0AQ4_2rCRirw-kD



## 部署教程

### 后台

- 下载idea、jdk1.8、mysql5.7+

- 使用idea打开项目x-mall文件夹里的end

  ![](https://www.hualigs.cn/image/60a28e279ea37.jpg)

- 导入项目的maven依赖

  ![](https://www.hualigs.cn/image/60a28ed768280.jpg)

- 启动数据库，新建数据库x-mall

  ![](https://www.hualigs.cn/image/60a28f3e62204.jpg)

- 导入sql脚本 `x-mall.sql`

  ![](https://www.hualigs.cn/image/60a28f5e1747f.jpg)

- 配置项目的数据库连接：

  ![](https://www.hualigs.cn/image/60a28fdbc1060.jpg)

- 点击启动按钮启动后台服务

  ![image-20210517234819044](C:\Users\xqnode\AppData\Roaming\Typora\typora-user-images\image-20210517234819044.png)

- 当你看到这个界面，说明启动成功了

  ![](https://www.hualigs.cn/image/60a29060d131c.jpg)

  服务端口：**9999**，可以自己修改`application.yml`文件进行配置



### 前台

- 使用idea或vscode打开项目front

![](https://www.hualigs.cn/image/60a290e357fbc.jpg)



- 配置npm源为国内镜像，否则下载会很慢（必须先下载安装好node）

  ```
  npm config set registry https://registry.npm.taobao.org
  ```

- 执行`npm i`命令进行依赖的安装

  ```
  npm i
  ```

- 执行`npm run serve` 运行项目

  ```
  npm run serve
  ```

  ![](https://www.hualigs.cn/image/60a2924fc3203.jpg)

- 看到这个表示启动成功，启动成功后页面自动跳转

  ![](https://www.hualigs.cn/image/60a29280dfcf3.jpg)

  ![](https://www.hualigs.cn/image/60a292a072aec.jpg)



## 数据库设计

### 用户表

| 名称      | 类型    | 长度 | 默认值           | 注释   |
| --------- | ------- | ---- | ---------------- | ------ |
| id        | bigint  | 20   | NOT NULL（自增） | 主键   |
| username  | varchar | 255  | NOT NULL         | 用户名 |
| nick_name | varchar | 255  | NULL             | 昵称   |
| password  | varchar | 255  | NOT NULL         | 密码   |
| sex       | varchar | 1    | NULL             | 性别   |
| age       | int     | 11   | NULL             | 年龄   |
| address   | varchar | 500  | NULL             | 地址   |
| email     | varchar | 255  | NULL             | 邮箱   |
| phone     | varchar | 255  | NULL             | 电话   |
| avatar    | varchar | 255  | NULL             | 头像   |
| role      | varchar | 255  | NULL             | 角色   |

### 角色表

| 名称        | 类型    | 长度 | 默认值           | 注释     |
| ----------- | ------- | ---- | ---------------- | -------- |
| id          | bigint  | 20   | NOT NULL（自增） | 主键     |
| name        | varchar | 255  | NULL             | 名称     |
| description | varchar | 255  | NULL             | 描述     |
| permission  | varchar | 255  | NULL             | 菜单列表 |

### 菜单表

| 名称        | 类型    | 长度 | 默认值           | 注释 |
| ----------- | ------- | ---- | ---------------- | ---- |
| id          | bigint  | 20   | NOT NULL（自增） | 主键 |
| name        | varchar | 255  | NULL             | 名称 |
| description | varchar | 255  | NULL             | 描述 |
| path        | varchar | 255  | NULL             | 路径 |
| icon        | varchar | 255  | NULL             | 图标 |

### 公告表

| 名称    | 类型    | 长度 | 默认值           | 注释     |
| ------- | ------- | ---- | ---------------- | -------- |
| id      | bigint  | 20   | NOT NULL（自增） | 主键     |
| title   | varchar | 255  | NULL             | 名称     |
| content | varchar | 255  | NULL             | 内容     |
| time    | varchar | 255  | NULL             | 发布时间 |

### 日志表

| 名称    | 类型    | 长度 | 默认值           | 注释     |
| ------- | ------- | ---- | ---------------- | -------- |
| id      | bigint  | 20   | NOT NULL（自增） | 主键     |
| content | varchar | 255  | NULL             | 操作内容 |
| time    | varchar | 255  | NULL             | 操作时间 |
| user    | varchar | 255  | NULL             | 操作人   |
| ip      | varchar | 255  | NULL             | 操作人ip |

### 轮播图

| 名称 | 类型    | 长度 | 默认值           | 注释 |
| ---- | ------- | ---- | ---------------- | ---- |
| id   | bigint  | 20   | NOT NULL（自增） | 主键 |
| img  | varchar | 255  | 图片             |      |
| url  | varchar | 20   | 关联链接         |      |

### 商品分类

| 名称 | 类型    | 长度 | 默认值           | 注释 |
| ---- | ------- | ---- | ---------------- | ---- |
| id   | bigint  | 20   | NOT NULL（自增） | 主键 |
| name | varchar | 255  | NULL             | 名称 |
| no   | varchar | 255  | NULL             | 编号 |

### 商品详情

| 名称        | 类型    | 长度 | 默认值           | 注释     |
| ----------- | ------- | ---- | ---------------- | -------- |
| id          | bigint  | 20   | NOT NULL（自增） | 主键     |
| name        | varchar | 255  | NULL             | 名称     |
| description | varchar | 1000 | NULL             | 描述     |
| no          | varchar | 255  | NULL             | 编号     |
| price       | decimal | 10,2 | 0.00             | 原价     |
| discount    | double  | 10,2 | 1                | 折扣     |
| store       | int     | 11   | 0                | 库存     |
| praise      | int     | 11   | 0                | 点赞数   |
| sales       | int     | 11   | 0                | 销量     |
| category_id | bigint  | 20   | NULL             | 分类id   |
| imgs        | varchar | 255  | NULL             | 图片地址 |
| create_time | varchar | 255  | NULL             | 创建时间 |

### 商品评论

| 名称        | 类型    | 长度 | 默认值           | 注释     |
| ----------- | ------- | ---- | ---------------- | -------- |
| id          | bigint  | 20   | NOT NULL（自增） | 主键     |
| content     | varchar | 1000 | NULL             | 内容     |
| username    | varchar | 255  | NULL             | 评价人   |
| avatar      | varchar | 255  | NULL             | 头像     |
| parent_id   | bigint  | 20   | NULL             | 父id     |
| foreign_id  | bigint  | 20   | NOT NULL         | 关联id   |
| type        | varchar | 255  | NOT NULL         | 模块     |
| create_time | varchar | 255  | NULL             | 创建时间 |

### 订单

| 名称         | 类型    | 长度 | 默认值           | 注释     |
| ------------ | ------- | ---- | ---------------- | -------- |
| id           | bigint  | 20   | NOT NULL（自增） | 主键     |
| order_no     | varchar | 20   | NULL             | 订单编号 |
| total_price  | decimal | 10,2 | NULL             | 总价     |
| user_id      | bigint  | 0    | NULL             | 下单人id |
| link_user    | varchar | 255  | NULL             | 联系人   |
| link_phone   | varchar | 255  | NULL             | 联系电话 |
| link_address | varchar | 255  | NULL             | 送货地址 |
| state        | varchar | 255  | '未付款'         | 状态     |
| create_time  | varchar | 255  | NULL             | 创建时间 |

### 订单商品关联表

| 名称     | 类型   | 长度 | 默认值           | 注释     |
| -------- | ------ | ---- | ---------------- | -------- |
| id       | bigint | 20   | NOT NULL（自增） | 主键     |
| goods_id | bigint | 20   | NOT NULL         | 商品id   |
| order_id | bigint | 20   | NOT NULL         | 订单id   |
| count    | int    | 11   | NOT NULL         | 商品数量 |

### 购物车

| 名称     | 类型   | 长度 | 默认值           | 注释     |
| -------- | ------ | ---- | ---------------- | -------- |
| id       | bigint | 20   | NOT NULL（自增） | 主键     |
| goods_id | bigint | 20   | NOT NULL         | 商品id   |
| user_id  | bigint | 20   | NOT NULL         | 用户id   |
| count    | int    | 11   | NOT NULL         | 商品数量 |

### 收货地址

| 名称         | 类型    | 长度 | 默认值           | 注释           |
| ------------ | ------- | ---- | ---------------- | -------------- |
| id           | bigint  | 20   | NOT NULL（自增） | 主键           |
| link_user    | varchar | 255  | NULL             | 联系人         |
| link_address | varchar | 255  | NULL             | 联系地址       |
| link_phone   | varchar | 255  | NULLNULL         | 联系人联系方式 |

### 收藏夹

| 名称        | 类型    | 长度 | 默认值           | 注释     |
| ----------- | ------- | ---- | ---------------- | -------- |
| id          | bigint  | 20   | NOT NULL（自增） | 主键     |
| name        | varchar | 255  | NULL             | 名称     |
| url         | varchar | 500  | NULL             | 链接     |
| user_id     | bigint  | 20   | NULL             | 收藏人   |
| create_time | varchar | 255  | NULL             | 收藏时间 |