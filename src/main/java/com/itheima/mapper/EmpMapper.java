package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    /**
     * 按条件查询所有员工及对应的部门信息
     */
    List<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "values (#{username}, #{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void save(Emp emp);

    /**
     * 批量删除员工信息
     */
    void deleteByIds(@Param("ids") List<Integer> ids);

    /**
     * 根据ID查询员工详细信息
     */
    Emp getById(Integer id);

    /**
     * 更新员工基本信息
     * @param emp
     */
    void updateById(Emp emp);

    /**
     * 统计员工性别数据
     * @return 员工性别统计数据
     */
    List<Map<String, Object>> countByGender();

    /**
     * 统计员工职位人数数据
     * @return 员工职位人数统计数据
     */
    List<Map<String, Object>> countByJob();
    
    /**
     * 根据用户名查询员工
     * @param username 用户名
     * @return 员工信息
     */
    @Select("select * from emp where username = #{username}")
    Emp getByUsername(String username);

    /**
     * 查询所有员工信息
     * @return 所有员工信息
     */
    List<Emp> list();
}

