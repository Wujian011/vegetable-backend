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
import com.wj.vegetablebackend.model.dto.classification.ClassificationAddRequest;
import com.wj.vegetablebackend.model.dto.classification.ClassificationQueryRequest;
import com.wj.vegetablebackend.model.dto.classification.ClassificationUpdateRequest;
import com.wj.vegetablebackend.model.entity.Classification;
import com.wj.vegetablebackend.model.entity.User;
import com.wj.vegetablebackend.model.vo.ClassificationVO;
import com.wj.vegetablebackend.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wj.vegetablebackend.service.ClassificationService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜品分类 控制层。
 *
 * @author wj
 */
@RestController
@RequestMapping("/classification")
public class ClassificationController {

    @Resource
    private ClassificationService classificationService;

    @Resource
    private UserService userService;

    /**
     * 获取所有分类
     * @return
     */
    @GetMapping("/getClassificationItem")
    public BaseResponse<List<Classification>> getClassificationItem() {
        List<Classification> classificationList = classificationService.list();

        return ResultUtils.success(classificationList);
    }


    /**
     * 创建菜品分类
     */
    @PostMapping("/add")
    public BaseResponse<Long> addClassification(@RequestBody ClassificationAddRequest classificationAddRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(classificationAddRequest == null, ErrorCode.PARAMS_ERROR);
        Classification classification = new Classification();
        BeanUtil.copyProperties(classificationAddRequest, classification);
        User loginUser = userService.getLoginUser(request);
        Long userId= loginUser.getId();
        classification.setUserId(userId);
        boolean result = classificationService.save(classification);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(classification.getId());
    }

    /**
     * 根据 id 获取菜品分类（仅管理员）
     */
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Classification> getClassificationById(long id) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        Classification classification = classificationService.getById(id);
        ThrowUtils.throwIf(classification == null, ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.success(classification);
    }

    /**
     * 根据 id 获取包装类
     */
    @GetMapping("/get/vo")
    public BaseResponse<ClassificationVO> getClassificationVOById(long id) {
        BaseResponse<Classification> response = getClassificationById(id);
        Classification classification = response.getData();
        return ResultUtils.success(classificationService.getClassificationVO(classification));
    }

    /**
     * 删除菜品分类
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteClassification(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = classificationService.removeById(deleteRequest.getId());
        return ResultUtils.success(b);
    }

    /**
     * 更新菜品分类
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateClassification(@RequestBody ClassificationUpdateRequest classificationUpdateRequest) {
        if (classificationUpdateRequest == null || classificationUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Classification classification = new Classification();
        BeanUtil.copyProperties(classificationUpdateRequest, classification);
        boolean result = classificationService.updateById(classification);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * 分页获取菜品分类封装列表（仅管理员）
     *
     * @param classificationQueryRequest 查询请求参数
     */
    @PostMapping("/list/page/vo")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<ClassificationVO>> listClassificationVOByPage(@RequestBody ClassificationQueryRequest classificationQueryRequest) {
        ThrowUtils.throwIf(classificationQueryRequest == null, ErrorCode.PARAMS_ERROR);
        long pageNum = classificationQueryRequest.getPageNum();
        long pageSize = classificationQueryRequest.getPageSize();
        Page<Classification> classificationPage = classificationService.page(Page.of(pageNum, pageSize),
                classificationService.getQueryWrapper(classificationQueryRequest));
        // 数据脱敏
        Page<ClassificationVO> classificationVOPage = new Page<>(pageNum, pageSize, classificationPage.getTotalRow());
        List<ClassificationVO> classificationVOList = classificationService.getClassificationVOList(classificationPage.getRecords());
        classificationVOPage.setRecords(classificationVOList);
        return ResultUtils.success(classificationVOPage);
    }

}
