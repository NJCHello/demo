package com.example.easyexcel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EasyexcelApplicationTests {

    @Test
    void contextLoads() {
        try {
            //ClassPathResource classPathResource  = new ClassPathResource("template/XXXXXX.xlsx");
            //String fileName = Test.class.getResource("/").getPath() + "template" + File.separator + "XXXXXX.xlsx";
            // 这里 只要，然后读取第一个sheet 同步读取会自动finish
            //EasyExcel.read(classPathResource.getInputStream(), new DemoDataListener(xxMapper,xxMapper,xxMapper,xxMapper)).sheet(0).doRead();
            //EasyExcel.read(classPathResource.getInputStream(), new DemoDataListener(xxMapper,xxMapper,xxMapper,xxMapper)).sheet(2).doRead();
            //EasyExcel.read(classPathResource.getInputStream(), new DemoDataListener(xxMapper,xxMapper,xxMapper,xxMapper)).sheet(3).doRead();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
