package com.example.easyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.example.easyexcel.dto.DemoSingleVO;
import com.example.easyexcel.enums.DemoTypeEnum;
import com.example.easyexcel.listener.DemoListener;
import com.example.easyexcel.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author chenghao
 * @since 2024/6/4 19:44
 */
@Slf4j
@RequestMapping("/upload/")
@RestController
public class UploadController {


    @Resource
    private DemoService demoService;

    @PostMapping("upload")
    public String upload(MultipartFile file) {
        try {
            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
            EasyExcel.read(file.getInputStream(),
                            DemoSingleVO.class,
                            new DemoListener(demoService, DemoTypeEnum.SINGLE, null))
                    .sheet().headRowNumber(1).doRead();
            return "success";
        } catch (Exception e) {
            log.error("数据导入异常", e);
            return "failure";
        }
    }
}
