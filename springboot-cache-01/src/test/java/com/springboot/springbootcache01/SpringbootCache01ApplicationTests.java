package com.springboot.springbootcache01;

import com.springboot.springbootcache01.bean.Emplovee;
import com.springboot.springbootcache01.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootCache01ApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    void contextLoads() {

        Emplovee emplovee=employeeMapper.getEmpById(1);
        System.out.println(emplovee);
    }

}
