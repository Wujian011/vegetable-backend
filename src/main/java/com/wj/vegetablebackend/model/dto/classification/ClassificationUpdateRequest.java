package com.wj.vegetablebackend.model.dto.classification;


import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import lombok.Data;

import java.io.Serializable;

/**
 * 分类修改
 */
@Data
public class ClassificationUpdateRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类代码
     */
    private String code;
}
