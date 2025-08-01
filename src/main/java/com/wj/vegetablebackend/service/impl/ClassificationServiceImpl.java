package com.wj.vegetablebackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.wj.vegetablebackend.exception.BusinessException;
import com.wj.vegetablebackend.exception.ErrorCode;
import com.wj.vegetablebackend.model.dto.classification.ClassificationQueryRequest;
import com.wj.vegetablebackend.model.entity.Classification;
import com.wj.vegetablebackend.mapper.ClassificationMapper;
import com.wj.vegetablebackend.model.vo.ClassificationVO;
import com.wj.vegetablebackend.service.ClassificationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜品分类 服务层实现。
 *
 * @author wj
 */
@Service
public class ClassificationServiceImpl extends ServiceImpl<ClassificationMapper, Classification>  implements ClassificationService{

    @Override
    public QueryWrapper getQueryWrapper(ClassificationQueryRequest classificationQueryRequest) {
        if (classificationQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = classificationQueryRequest.getId();
        String name = classificationQueryRequest.getName();
        String code = classificationQueryRequest.getCode();
        String sortField = classificationQueryRequest.getSortField();
        String sortOrder = classificationQueryRequest.getSortOrder();
        return QueryWrapper.create()
                .eq("id", id) // where id = ${id}
                .like("code", code)
                .like("name", name)
                .orderBy(sortField, "ascend".equals(sortOrder));
    }

    @Override
    public ClassificationVO getClassificationVO(Classification classification) {
        if (classification == null) {
            return null;
        }
        ClassificationVO classificationVO = new ClassificationVO();
        BeanUtil.copyProperties(classification, classificationVO);
        return classificationVO;
    }

    @Override
    public List<ClassificationVO> getClassificationVOList(List<Classification> classificationList) {
        if (CollUtil.isEmpty(classificationList)) {
            return new ArrayList<>();
        }
        return classificationList.stream()
                .map(this::getClassificationVO)
                .collect(Collectors.toList());
    }
}
