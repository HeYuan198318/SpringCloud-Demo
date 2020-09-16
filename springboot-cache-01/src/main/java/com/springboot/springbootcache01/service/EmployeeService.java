package com.springboot.springbootcache01.service;

import com.springboot.springbootcache01.bean.Emplovee;
import com.springboot.springbootcache01.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * @author C3006248
 * @create 2020/8/31  11:43
 */
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 將方法的返回結果進行緩存，以後再要相同的數據，直接從緩存中獲取，不用調用方法。
     *
     * CacheManager管理多個cache組件，對緩存的真正crud操作在cache組件中，每一個緩存組件有自己唯一一個名字
     *
     * 幾個屬性：
     * cacheName/value 指定緩存的名字；
     * key:緩存數據使用的key,可以用它來指定。默認使用方法參數的值 1-方法返回的值
     *     編寫spel #id參數id的值 #a0 #root.args[0]
     * keyGenerator：key的生成器，可以指定key的生成器的組件id
     *     key/keyGenerator二選一
     * cachemanager:指定緩存管理器，或者指定緩存解析器cacheResolver
     * condition:指定符合條件的情況下才緩存
     * unless:否定緩存,當unless指定的條件為true，方法的返回值就不會被緩存,可以獲取結果進行判斷
     *        unless = "#result==null"
     * sysnc:是否使用異步模式
     *
     *原理
     * 1.自動配置類:CacheAutoConfiguration
     * 2.緩存的配置類
     *      org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
     * 1 = "org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration"
     * 2 = "org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration"
     * 3 = "org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration"
     * 4 = "org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration"
     * 5 = "org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration"
     * 6 = "org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration"
     * 7 = "org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration"
     * 8 = "org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration"
     * 9 = "org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration"
     *
     * 那個配置類默認生效
     *   @ConditionalOnBean(); SimpleCacheConfiguration
     *
     */
//    key = "#result.methodName+'['+#id+']'"
    @Cacheable(cacheNames = {"emp"},key = "#id"/*,keyGenerator = "mykeyGenerator",condition = "#a0>1",unless = "#a0==2"*/)
    public Emplovee getempById(Integer id){
        System.out.println("查詢員工");
        Emplovee emplovee=employeeMapper.getEmpById(id);
        return emplovee;
    }
    /**
     * @CachePut 同步更新緩存
     */
    @CachePut(value = "emp",key = "1")
    public Emplovee updateEmp(Emplovee emplovee){
        System.out.println("updateEmp"+emplovee);
        employeeMapper.updateById(emplovee);
        return emplovee;
    }

    /*
    清除緩存
     */
    @CacheEvict(value = "emp",key = "#id",beforeInvocation =true)
    public void deleteEmp(Integer id){
        System.out.println("deleteEmpp"+id);
        //employeeMapper.delEmpById(id);
    }

    @Caching(
            cacheable = {
                    @Cacheable(value = "emp",key = "#lastName")
            },put = {
            @CachePut(value = "emp", key = "#result.empId"),
            @CachePut(value ="emp",key = "#result.empName")

    }
    )
    public Emplovee getEmpByLastName(String lastName){
       return employeeMapper.getEmpByLastName(lastName);
    }
}
