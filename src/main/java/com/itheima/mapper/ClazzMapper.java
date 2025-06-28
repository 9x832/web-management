package com.itheima.mapper;

import com.itheima.pojo.ClaQueryParam;
import com.itheima.pojo.Clazz;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 按条件对班级进行分页查询
     */
    List<Clazz> page(ClaQueryParam claQueryParam);

    /**
     * 根据ID删除班级
     * @param id
     */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    /**
     * 添加班级
     * @param clazz
     */
    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) " +
            "VALUES (#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void save(Clazz clazz);

    /**
     * 根据ID查找班级
     * @param id
     * @return
     */
    @Select("select * from clazz where id = #{id}")
    Clazz findById(Integer id);

    /**
     * 更新班级信息
     * @param clazz
     */
    void update(Clazz clazz);

    List<Clazz> list();
}
