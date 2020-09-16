package com.springboot.springbootcache01.Controller;

import com.springboot.springbootcache01.bean.Emplovee;
import com.springboot.springbootcache01.mapper.EmployeeMapper;
import com.springboot.springbootcache01.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author C3006248
 * @create 2020/8/31  11:22
 */
@RestController
public class EmploveeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/emp/{id}")
    public Emplovee getById(@PathVariable("id") Integer id){
        Emplovee emplovee=employeeService.getempById(id);
        return emplovee;
    }

    @RequestMapping("/emp")
    public Emplovee updateEmp(@RequestBody Emplovee emplovee){
        Emplovee emp=employeeService.updateEmp(emplovee);
        return emp;

    }

    @GetMapping("/delEmp/{id}")
    public String delteEmp(@PathVariable("id")Integer id){
        employeeService.deleteEmp(id);
        return "刪除成功";
    }

    @RequestMapping("/emp/lastName/{lastName}")
    public Emplovee getById(@PathVariable("lastName") String lastName){
        Emplovee emplovee=employeeService.getEmpByLastName(lastName);
        return emplovee;
    }

}
