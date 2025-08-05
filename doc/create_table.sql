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
    id          bigint auto_increment comment 'id' primary key,
    orderNumber varchar(256)                       not null comment '订单编号',
    price       decimal(10, 2)                     not null comment '价格',
    userId      bigint                             not null comment '创建用户id',
    editTime    datetime default CURRENT_TIMESTAMP not null comment '编辑时间',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除'
) comment '订单' collate = utf8mb4_unicode_ci;

create table if not exists `order_details`
(
    id         bigint auto_increment comment 'id' primary key,
    orderId    bigint                             not null comment '订单Id',
    dishesId   bigint                             not null comment '菜品Id',
    price      decimal(10, 2)                     not null comment '价格（当时价格）',
    number     integer                            not null comment '数量',
    userId     bigint                             not null comment '创建用户id',
    editTime   datetime default CURRENT_TIMESTAMP not null comment '编辑时间',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '订单详情' collate = utf8mb4_unicode_ci;

-- 菜品表添加图片字段
alter table dishes
    add column dishesImage varchar(1024) null comment '菜品图片';

-- 用户表添加绑定关系字段，和情侣角色字段
alter table user
    add column partnerId         bigint       null comment '情侣id',
    add column partnerBindTime   datetime     null comment '绑定时间',
    add column inviteCode        varchar(256) null comment '个人邀请码（用于被邀请绑定）',
    add column coupleRole        varchar(256) null default 'feeder' comment 'feeder(饲养员)/foodie(吃货)',
    add column coupleRoleSetTime datetime     null on update CURRENT_TIMESTAMP comment '角色设定时间';


