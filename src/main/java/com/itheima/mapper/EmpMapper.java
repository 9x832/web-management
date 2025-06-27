package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    /**
     * 按条件查询所有员工及对应的部门信息
     */
    List<Emp> list(EmpQueryParam empQueryParam);

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
}

