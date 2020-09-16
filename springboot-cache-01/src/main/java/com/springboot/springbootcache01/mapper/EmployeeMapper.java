package com.springboot.springbootcache01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.springbootcache01.bean.Emplovee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author C3006248
 * @create 2020/8/31  11:11
 */

@Mapper
@Repository
public interface EmployeeMapper extends BaseMapper<Emplovee> {

    @Select("select * from tbl_emp where emp_id=#{id}")
    public Emplovee getEmpById(Integer id);

    @Delete("DELETE FROM tbl_emp WHERE emp_id=#{id}")
    public void delEmpById(Integer id);

    @Select("select * from tbl_emp where emp_name=#{lastName}")
    Emplovee getEmpByLastName(String lastName);
}
