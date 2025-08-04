package com.wj.vegetablebackend.controller;

import com.wj.vegetablebackend.common.BaseResponse;
import com.wj.vegetablebackend.common.ResultUtils;
import com.wj.vegetablebackend.manage.FileManage;
import com.wj.vegetablebackend.model.entity.User;
import com.wj.vegetablebackend.service.UserService;
import io.micrometer.core.instrument.binder.BaseUnits;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileManage fileManage;

    @Resource
    private UserService userService;



    @PostMapping(value = "/upload",consumes = "multipart/form-data")
    public BaseResponse<String> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        Long loginUserId = loginUser.getId();
        String fileUrl = fileManage.upload(file, loginUserId);
        return ResultUtils.success(fileUrl);
    }


}
