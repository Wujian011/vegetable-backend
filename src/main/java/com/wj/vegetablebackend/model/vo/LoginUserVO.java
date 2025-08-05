package com.wj.vegetablebackend.model.vo;

import com.mybatisflex.annotation.Column;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 脱敏后的登录用户信息
 */
@Data
public class LoginUserVO implements Serializable {

    /**
     * 用户 id
     */
    private Long id;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin
     */
    private String userRole;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 情侣id
     */
    private LoginUserVO partnerUser;

    /**
     * 绑定时间
     */
    private LocalDateTime partnerBindTime;

    /**
     * 个人邀请码（用于被邀请绑定）
     */
    private String inviteCode;

    /**
     * feeder(饲养员)/foodie(吃货)
     */
    private String coupleRole;


    private static final long serialVersionUID = 1L;
}