package com.example.demo.io;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

public class User01 implements Serializable {
    private long id;
    private String name;
    public User01() {}
    public User01(long id, String name) {
        this.id = id;
        this.name = name;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    // 序列化为字节数组
    public byte[] serialize() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Hessian2Output hessian2Output = new Hessian2Output(baos);
        hessian2Output.writeObject(this);
        hessian2Output.flush();
        return baos.toByteArray();
    }
    // 从字节数组反序列化
    public static User01 deserialize(byte[] bytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Hessian2Input hessian2Input = new Hessian2Input(bais);
        return (User01) hessian2Input.readObject();
    }

    public static void main(String[] args) throws IOException {
        User01 user = new User01(1L, "Tom");
        byte[] bytes = user.serialize();
        System.out.println(bytes.length);
        System.out.println("序列化后的字节数组：" + Arrays.toString(bytes));
        User01 newUser = User01.deserialize(bytes);
        System.out.println("反序列化后的对象：" + new Gson().toJson(newUser));
    }
}
