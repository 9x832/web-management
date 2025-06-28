package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    /**
     * 按条件查询所有学员
     */
    List<Student> list(StudentQueryParam studentQueryParam);

    /**
     * 根据ID查询学员
     */
    @Select("select s.*, c.name as clazzName from student s left join clazz c on s.clazz_id = c.id where s.id = #{id}")
    Student getById(Integer id);

    /**
     * 新增学员
     */
    @Insert("insert into student(name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, violation_count, violation_score, create_time, update_time) " +
            "values(#{name}, #{no}, #{gender}, #{phone}, #{idCard}, #{isCollege}, #{address}, #{degree}, #{graduationDate}, #{clazzId}, 0, 0, now(), now())")
    void save(Student student);

    /**
     * 更新学员信息
     */
    void update(Student student);

    /**
     * 批量删除学员
     */
    void deleteByIds(@Param("ids") List<Integer> ids);
    
    /**
     * 违纪处理
     */
    @Update("update student set violation_count = violation_count + 1, violation_score = violation_score + #{score}, update_time = now() where id = #{id}")
    void addViolation(@Param("id") Integer id, @Param("score") Short score);

    /**
     * 统计学员学历数据
     * @return 学员学历统计数据
     */
    List<Map<String, Object>> countByDegree();

    /**
     * 统计班级人数数据
     * @return 班级人数统计数据
     */
    List<Map<String, Object>> countByClazz();
} 