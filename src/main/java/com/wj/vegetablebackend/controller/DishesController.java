package com.wj.vegetablebackend.controller;

import cn.hutool.core.bean.BeanUtil;
import com.mybatisflex.core.paginate.Page;
import com.wj.vegetablebackend.annotation.AuthCheck;
import com.wj.vegetablebackend.common.BaseResponse;
import com.wj.vegetablebackend.common.DeleteRequest;
import com.wj.vegetablebackend.common.ResultUtils;
import com.wj.vegetablebackend.constant.UserConstant;
import com.wj.vegetablebackend.exception.BusinessException;
import com.wj.vegetablebackend.exception.ErrorCode;
import com.wj.vegetablebackend.exception.ThrowUtils;
import com.wj.vegetablebackend.model.dto.dishes.DishesAddRequest;
import com.wj.vegetablebackend.model.dto.dishes.DishesQueryRequest;
import com.wj.vegetablebackend.model.dto.dishes.DishesUpdateRequest;
import com.wj.vegetablebackend.model.entity.Dishes;
import com.wj.vegetablebackend.model.vo.DishesVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wj.vegetablebackend.service.DishesService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 菜品 控制层。
 *
 * @author wj
 */
@RestController
@RequestMapping("/dishes")
public class DishesController {

    @Resource
    private DishesService dishesService;

    /**
     * 创建菜品
     */
    @PostMapping("/add")
    public BaseResponse<Long> addDishes(@RequestBody DishesAddRequest dishesAddRequest) {
        ThrowUtils.throwIf(dishesAddRequest == null, ErrorCode.PARAMS_ERROR);
        Dishes dishes = new Dishes();
        BeanUtil.copyProperties(dishesAddRequest, dishes);

        boolean result = dishesService.save(dishes);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(dishes.getId());
    }

    /**
     * 根据 id 获取菜品（仅管理员）
     */
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Dishes> getDishesById(long id) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        Dishes dishes = dishesService.getById(id);
        ThrowUtils.throwIf(dishes == null, ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.success(dishes);
    }

    /**
     * 根据 id 获取包装类
     */
    @GetMapping("/get/vo")
    public BaseResponse<DishesVO> getDishesVOById(long id) {
        BaseResponse<Dishes> response = getDishesById(id);
        Dishes dishes = response.getData();
        return ResultUtils.success(dishesService.getDishesVO(dishes));
    }

    /**
     * 删除菜品
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteDishes(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = dishesService.removeById(deleteRequest.getId());
        return ResultUtils.success(b);
    }

    /**
     * 更新菜品
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateDishes(@RequestBody DishesUpdateRequest dishesUpdateRequest) {
        if (dishesUpdateRequest == null || dishesUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Dishes dishes = new Dishes();
        BeanUtil.copyProperties(dishesUpdateRequest, dishes);
        boolean result = dishesService.updateById(dishes);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * 分页获取菜品封装
     * 列表（仅管理员）
     *
     * @param dishesQueryRequest 查询请求参数
     */
    @PostMapping("/list/page/vo")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<DishesVO>> listDishesVOByPage(@RequestBody DishesQueryRequest dishesQueryRequest) {
        ThrowUtils.throwIf(dishesQueryRequest == null, ErrorCode.PARAMS_ERROR);
        long pageNum = dishesQueryRequest.getPageNum();
        long pageSize = dishesQueryRequest.getPageSize();
        Page<Dishes> dishesPage = dishesService.page(Page.of(pageNum, pageSize),
                dishesService.getQueryWrapper(dishesQueryRequest));
        // 数据脱敏
        Page<DishesVO> dishesVOPage = new Page<>(pageNum, pageSize, dishesPage.getTotalRow());
        List<DishesVO> dishesVOList = dishesService.getDishesVOList(dishesPage.getRecords());
        dishesVOPage.setRecords(dishesVOList);
        return ResultUtils.success(dishesVOPage);
    }

}
