package com.wj.vegetablebackend.model.dto.user;

import com.mybatisflex.annotation.Column;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户编辑请求
 */
@Data
public class UserEditeRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 简介
     */
    private String userProfile;

    /**
     * feeder(饲养员)/foodie(吃货)
     */
    private String coupleRole;

    @Serial
    private static final long serialVersionUID = 1L;
}