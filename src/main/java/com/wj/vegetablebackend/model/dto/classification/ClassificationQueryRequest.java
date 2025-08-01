package com.wj.vegetablebackend.model.dto.classification;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.wj.vegetablebackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 分类查询参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ClassificationQueryRequest  extends PageRequest implements Serializable {
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
