package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Student;
import com.example.demo.mybatis.anno.ExtendedQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface StudentMapper2 extends BaseMapper<Student> {
    @ExtendedQuery("fuzzy")
    @Select("SELECT * FROM student WHERE name = #{name}")
    List<Student> searchByName(@Param("name") String name);
}
