package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    FooService fooService;
    @Test
    void findByFilter(){
        List<FooEntity> result = fooService.search(new FooService.FooFilters("first"));
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getComment()).isEqualTo("first comment");
    }

}
