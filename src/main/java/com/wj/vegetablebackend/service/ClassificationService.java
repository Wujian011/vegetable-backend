package com.wj.vegetablebackend.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.wj.vegetablebackend.model.dto.classification.ClassificationQueryRequest;
import com.wj.vegetablebackend.model.entity.Classification;
import com.wj.vegetablebackend.model.vo.ClassificationVO;

import java.util.List;

/**
 * 菜品分类 服务层。
 *
 * @author wj
 */
public interface ClassificationService extends IService<Classification> {
    /**
     * 根据查询条件构造数据查询参数
     *
     * @param classificationQueryRequest
     * @return
     */
    QueryWrapper getQueryWrapper(ClassificationQueryRequest classificationQueryRequest);


    /**
     * 获取脱敏后的分类信息
     *
     * @param classification 分类信息
     * @return
     */
    ClassificationVO getClassificationVO(Classification classification);

    /**
     * 获取脱敏后的分类信息（分页）
     *
     * @param classificationList 分类列表
     * @return
     */
    List<ClassificationVO> getClassificationVOList(List<Classification> classificationList);
}
