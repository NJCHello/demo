package com.example.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.exception.ExcelAnalysisStopException;
import com.alibaba.excel.exception.ExcelRuntimeException;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.util.StringUtils;
import com.example.easyexcel.dto.DemoBatchVO;
import com.example.easyexcel.dto.DemoSingleVO;
import com.example.easyexcel.dto.DemoVO;
import com.example.easyexcel.enums.DemoTypeEnum;
import com.example.easyexcel.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * RFID标签导入
 * @author chenghao
 * @since 2024-04-22 11:31:18
 */
@Slf4j
public class DemoListener implements ReadListener<Object> {

    private DemoService demoService;

    private DemoTypeEnum demoTypeEnum;

    private String userName;

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    private int count = 0;

    /**
     * 缓存的数据,List<AttdnOver>
     */
    private List<DemoVO> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    public DemoListener() {
    }

    public DemoListener(DemoService demoService, DemoTypeEnum demoTypeEnum, String userName) {
        this.demoService = demoService;
        this.demoTypeEnum = demoTypeEnum;
        this.userName = userName;
    }

    /**
     * 这个每一条数据解析都会来调用
     * @param
     * @param analysisContext
     */
    @Override
    public void invoke(Object demoVO, AnalysisContext analysisContext) {
        //log.info("解析到第 {} 条数据:{}", (++count), JSON.toJSONString(demoVO));
        //把表格对应的实体类对象转化成数据库表对应的对象
        parseByType(demoVO);
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        if (exception instanceof ExcelAnalysisStopException) {
            ExcelAnalysisStopException excelDataConvertException = (ExcelAnalysisStopException)exception;
            log.error("第{}行，第{}列解析异常，数据为:{}", excelDataConvertException.getMessage());
            cachedDataList = new ArrayList<>();
            throw new Exception(excelDataConvertException.getMessage());
        }
    }

    /**
     * 标签导入数据解析
     * @param demoVO
     */
    private void parseByType(Object demoVO) {
        switch (demoTypeEnum) {
            //单标签
            case SINGLE:
                if (demoVO instanceof DemoSingleVO) {
                    DemoSingleVO demoSingleVO = (DemoSingleVO) demoVO;
                    //初始化标签信息
                    DemoVO rfidEpc = new DemoVO();
                    cachedDataList.add(rfidEpc);
                }
                break;
                //盖板标签
            case COVER:
                if (demoVO instanceof DemoBatchVO) {
                    DemoBatchVO demoBatchVO = (DemoBatchVO) demoVO;
                    Class<?> clazz = demoBatchVO.getClass();
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        try {
                            Object fieldValue = field.get(demoBatchVO);
                            String epcCode = String.valueOf(fieldValue);
                            if (field.getName().startsWith("aa") && StringUtils.isNotBlank(epcCode)) {
                                String fieldName = field.getName();
                                String index = fieldName.replace("aa", "");
                                DemoVO rfidEpc = new DemoVO();
                                cachedDataList.add(rfidEpc);
                            }
                        } catch (IllegalAccessException e) {
                            log.error(e.getMessage());
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     * @param analysisContext
     */
     @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("表格中的所有数据解析完成！！！");
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
    }

    /**
     * 存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        if (CollectionUtils.isEmpty(cachedDataList)) {
            return;
        }
        try {
            demoService.batchSave(cachedDataList);
        } catch (Exception e) {
            throw new ExcelRuntimeException("数据入库异常");
        }
        log.info("存储数据库成功！");
    }
}
