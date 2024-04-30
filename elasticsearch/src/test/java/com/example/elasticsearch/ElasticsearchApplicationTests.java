package com.example.elasticsearch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import javax.annotation.Resource;

@SpringBootTest
class ElasticsearchApplicationTests {

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    void contextLoads() {

        elasticsearchRestTemplate.cluster();
    }

}
