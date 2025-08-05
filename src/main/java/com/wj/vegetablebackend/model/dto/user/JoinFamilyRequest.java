package com.wj.vegetablebackend.model.dto.user;

import lombok.Data;

/**
 * 加入家庭请求
 */
@Data
public class JoinFamilyRequest {

    /**
     * 关联id
     */
    private Long userId;

    /**
     * 情侣角色
     */
    private String coupleRole;
}
