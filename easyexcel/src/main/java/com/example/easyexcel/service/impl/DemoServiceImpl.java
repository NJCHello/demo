package com.example.easyexcel.service.impl;

import com.example.easyexcel.dto.DemoVO;
import com.example.easyexcel.service.DemoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenghao
 * @since 2024/6/4 19:59
 */
@Service
public class DemoServiceImpl implements DemoService {
    /**
     * 批量保存
     *
     * @param demoVOList
     * @return
     */
    @Override
    public boolean batchSave(List<DemoVO> demoVOList) {
        return false;
    }
}
