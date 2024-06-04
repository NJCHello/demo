package com.example.easyexcel.service;

import com.example.easyexcel.dto.DemoVO;

import java.util.List;

/**
 * @author chenghao
 * @since 2024/6/4 19:50
 */
public interface DemoService {

    /**
     * 批量保存
     * @param demoVOList
     * @return
     */
    boolean batchSave(List<DemoVO> demoVOList);
}
