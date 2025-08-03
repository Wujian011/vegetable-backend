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
import com.wj.vegetablebackend.model.dto.orders.OrdersAddRequest;
import com.wj.vegetablebackend.model.dto.orders.OrdersQueryRequest;
import com.wj.vegetablebackend.model.dto.orders.OrdersUpdateRequest;
import com.wj.vegetablebackend.model.entity.Orders;
import com.wj.vegetablebackend.model.entity.User;
import com.wj.vegetablebackend.model.vo.OrdersVO;
import com.wj.vegetablebackend.service.OrdersService;
import com.wj.vegetablebackend.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 订单 控制层。
 *
 * @author wj
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Resource
    private OrdersService ordersService;

    @Resource
    private UserService userService;

    /**
     * 创建订单
     */
    @PostMapping("/add")
    public BaseResponse<Long> addOrders(@RequestBody OrdersAddRequest ordersAddRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(ordersAddRequest == null, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);

        Long ordersId = ordersService.addOrders(ordersAddRequest, loginUser);
        return ResultUtils.success(ordersId);
    }

    /**
     * 根据 id 获取订单（仅管理员）
     */
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Orders> getOrdersById(long id) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        Orders orders = ordersService.getById(id);
        ThrowUtils.throwIf(orders == null, ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.success(orders);
    }

    /**
     * 根据 id 获取包装类
     */
    @GetMapping("/get/vo")
    public BaseResponse<OrdersVO> getOrdersVOById(long id) {
        BaseResponse<Orders> response = getOrdersById(id);
        Orders orders = response.getData();
        return ResultUtils.success(ordersService.getOrdersVO(orders));
    }

    /**
     * 删除订单
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteOrders(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = ordersService.removeById(deleteRequest.getId());
        return ResultUtils.success(b);
    }

    /**
     * 更新订单
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateOrders(@RequestBody OrdersUpdateRequest ordersUpdateRequest) {
        if (ordersUpdateRequest == null || ordersUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Orders orders = new Orders();
        BeanUtil.copyProperties(ordersUpdateRequest, orders);
        boolean result = ordersService.updateById(orders);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * 分页获取订单封装
     * 列表（仅管理员）
     *
     * @param ordersQueryRequest 查询请求参数
     */
    @PostMapping("/list/page/vo")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<OrdersVO>> listOrdersVOByPage(@RequestBody OrdersQueryRequest ordersQueryRequest) {
        ThrowUtils.throwIf(ordersQueryRequest == null, ErrorCode.PARAMS_ERROR);
        long pageNum = ordersQueryRequest.getPageNum();
        long pageSize = ordersQueryRequest.getPageSize();
        Page<Orders> ordersPage = ordersService.page(Page.of(pageNum, pageSize),
                ordersService.getQueryWrapper(ordersQueryRequest));
        // 数据脱敏
        Page<OrdersVO> ordersVOPage = new Page<>(pageNum, pageSize, ordersPage.getTotalRow());
        List<OrdersVO> ordersVOList = ordersService.getOrdersVOList(ordersPage.getRecords());
        ordersVOPage.setRecords(ordersVOList);
        return ResultUtils.success(ordersVOPage);
    }

    /**
     * 获取我创建的订单数据
     * 列表（仅管理员）
     *
     * @param ordersQueryRequest 查询请求参数
     */
    @PostMapping("/list/page/vo/my")
    public BaseResponse<Page<OrdersVO>> listOrdersVOPageByMy(@RequestBody OrdersQueryRequest ordersQueryRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(ordersQueryRequest == null, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        Long userId = loginUser.getId();
        ordersQueryRequest.setUserId(userId);
        long pageNum = ordersQueryRequest.getPageNum();
        long pageSize = ordersQueryRequest.getPageSize();
        Page<Orders> ordersPage = ordersService.page(Page.of(pageNum, pageSize),
                ordersService.getQueryWrapper(ordersQueryRequest));
        // 数据脱敏
        Page<OrdersVO> ordersVOPage = new Page<>(pageNum, pageSize, ordersPage.getTotalRow());
        List<OrdersVO> ordersVOList = ordersService.getOrdersVOList(ordersPage.getRecords());
        ordersVOPage.setRecords(ordersVOList);
        return ResultUtils.success(ordersVOPage);
    }
}
