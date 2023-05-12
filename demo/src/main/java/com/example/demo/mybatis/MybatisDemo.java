package com.example.demo.mybatis;

import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisDemo {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // 读取配置，构建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 生成Session实例
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取mapper代理类
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        Student student = studentMapper.selectByPrimaryKey(1);

        System.out.println(student);
    }
}
