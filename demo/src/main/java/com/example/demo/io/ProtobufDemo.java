package com.example.demo.io;

/**
 * @author caozhixin
 * @date 2023/3/23 14:11
 */
import com.example.demo.protobuf.PersonOuterClass;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
public class ProtobufDemo {
    public static void main(String[] args) throws IOException {
        // 创建 Protobuf 对象
        PersonOuterClass.Person.Builder builder = PersonOuterClass.Person.newBuilder();
        builder.setName("John");
        builder.setAge(30);
        PersonOuterClass.Person person = builder.build();
        // 对象序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        person.writeTo(bos);
        byte[] bytes = bos.toByteArray();
        System.out.println(bytes.length);
        bos.close();
        // 对象反序列化
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        PersonOuterClass.Person newPerson = PersonOuterClass.Person.parseFrom(bis);
        System.out.println(newPerson);
        bis.close();
    }
}
