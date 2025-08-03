-- 创建库
create database vegetable;

use vegetable;

-- 以下是建表语句

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin',
    editTime     datetime     default CURRENT_TIMESTAMP not null comment '编辑时间',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    UNIQUE KEY uk_userAccount (userAccount),
    INDEX idx_userName (userName)
) comment '用户' collate = utf8mb4_unicode_ci;

-- 菜品分类
create table if not exists classification
(
    id         bigint auto_increment comment 'id' primary key,
    name       varchar(256)                       not null comment '分类名称',
    code       varchar(512)                       not null comment '分类代码',
    userId     bigint                             not null comment '创建用户id',
    editTime   datetime default CURRENT_TIMESTAMP not null comment '编辑时间',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '菜品分类' collate = utf8mb4_unicode_ci;

-- 菜品
create table if not exists dishes
(
    id               bigint auto_increment comment 'id' primary key,
    classificationId bigint                             not null comment '菜品分类',
    name             varchar(256)                       not null comment '菜品名称',
    price            decimal(10, 2)                     not null comment '价格',
    material         text                               not null comment '材料',
    userId           bigint                             not null comment '创建用户id',
    editTime         datetime default CURRENT_TIMESTAMP not null comment '编辑时间',
    createTime       datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime       datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete         tinyint  default 0                 not null comment '是否删除'
) comment '菜品' collate = utf8mb4_unicode_ci;

-- 购物车
create table if not exists shopping_cart
(
    id         bigint auto_increment comment 'id' primary key,
    dishesId   bigint                             not null comment '菜品Id',
    number     integer                            not null comment '数量',
    userId     bigint                             not null comment '创建用户id',
    editTime   datetime default CURRENT_TIMESTAMP not null comment '编辑时间',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '购物车' collate = utf8mb4_unicode_ci;

-- 订单
create table if not exists orders
(
    id         bigint auto_increment comment 'id' primary key,
    dishesId   bigint                             not null comment '菜品Id',
    price      decimal(10, 2)                     not null comment '价格',
    userId     bigint                             not null comment '创建用户id',
    editTime   datetime default CURRENT_TIMESTAMP not null comment '编辑时间',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '订单' collate = utf8mb4_unicode_ci;

create table if not exists `order_details`
(
    id         bigint auto_increment comment 'id' primary key,
    dishesId   bigint                             not null comment '菜品Id',
    price      decimal(10, 2)                     not null comment '价格（当时价格）',
    number     integer                            not null comment '数量',
    userId     bigint                             not null comment '创建用户id',
    editTime   datetime default CURRENT_TIMESTAMP not null comment '编辑时间',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '订单详情' collate = utf8mb4_unicode_ci;