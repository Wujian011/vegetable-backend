package com.wj.vegetablebackend.manage;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.wj.vegetablebackend.exception.BusinessException;
import com.wj.vegetablebackend.exception.ErrorCode;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;

@Service
@Slf4j
public class FileManage {
    @Value("${minio.bucket}")
    private String bucketName;

    @Value("${minio.url}")
    private String url;

    @Resource
    private MinioClient minioClient;

    /**
     * 上传文件
     *
     * @param file
     * @param userId
     * @return
     */
    public String upload(MultipartFile file, Long userId) {

        // 生成文件名(日期文件夹)
        String suffix = FileUtil.getSuffix(file.getOriginalFilename());
        String uuid = IdUtil.simpleUUID();
        String fileName = userId + DateUtil.format(LocalDateTime.now(), "yyyyMMdd") + "/" + uuid + "." + suffix;
        //  try-with-resource 语法糖自动释放流
        try (InputStream inputStream = file.getInputStream()) {
            // 文件上传
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .contentType(file.getContentType())
                    .stream(inputStream, inputStream.available(), -1)
                    .build();
            minioClient.putObject(putObjectArgs);

            // 返回文件路径
            String fileUrl;
            GetPresignedObjectUrlArgs getPresignedObjectUrlArgs = GetPresignedObjectUrlArgs.builder()
                    .bucket(bucketName).object(fileName)
                    .method(Method.GET)
                    .build();

            //  minioClient.getPresignedObjectUrl(getPresignedObjectUrlArgs);
            fileUrl = url + File.separator + fileName;
            return fileUrl;
        } catch (Exception e) {
            log.error("上传文件失败：{}", e.getMessage());
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, e.getMessage());
        }
    }

}
