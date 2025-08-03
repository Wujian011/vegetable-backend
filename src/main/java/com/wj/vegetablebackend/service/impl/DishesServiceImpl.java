package com.wj.vegetablebackend.service.impl;

import java.math.BigDecimal;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.wj.vegetablebackend.exception.BusinessException;
import com.wj.vegetablebackend.exception.ErrorCode;
import com.wj.vegetablebackend.model.dto.dishes.DishesQueryRequest;
import com.wj.vegetablebackend.model.entity.Dishes;
import com.wj.vegetablebackend.mapper.DishesMapper;
import com.wj.vegetablebackend.model.vo.DishesVO;
import com.wj.vegetablebackend.service.DishesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜品 服务层实现。
 *
 * @author wj
 */
@Service
public class DishesServiceImpl extends ServiceImpl<DishesMapper, Dishes> implements DishesService {


    @Override
    public QueryWrapper getQueryWrapper(DishesQueryRequest dishesQueryRequest) {

        if (dishesQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }

        Long id = dishesQueryRequest.getId();
        String name = dishesQueryRequest.getName();
        BigDecimal price = dishesQueryRequest.getPrice();
        String material = dishesQueryRequest.getMaterial();
        Long classificationId = dishesQueryRequest.getClassificationId();
        String sortField = dishesQueryRequest.getSortField();
        String sortOrder = dishesQueryRequest.getSortOrder();
        return QueryWrapper.create()
                .eq("id", id) // where id = ${id}
                .eq("classificationId", classificationId)
                .eq("price", price)
                .like("name", name)
                .like("material", material)
                .orderBy(sortField, "ascend".equals(sortOrder));
    }

    @Override
    public DishesVO getDishesVO(Dishes dishes) {
        if (dishes == null) {
            return null;
        }
        DishesVO dishesVO = new DishesVO();
        BeanUtil.copyProperties(dishes, dishesVO);
        return dishesVO;
    }

    @Override
    public List<DishesVO> getDishesVOList(List<Dishes> dishesList) {
        if (CollUtil.isEmpty(dishesList)) {
            return new ArrayList<>();
        }
        return dishesList.stream()
                .map(this::getDishesVO)
                .collect(Collectors.toList());
    }

}
