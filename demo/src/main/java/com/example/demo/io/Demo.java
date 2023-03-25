package com.example.demo.io;

/**
 * @author caozhixin
 * @date 2023/3/24 20:39
 */

import java.io.*;
import java.net.*;

public class Demo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 图片读写
        FileInputStream fis = new FileInputStream("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/01.jpg");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        fis.close();
        bos.close();
        byte[] imageData = bos.toByteArray();
        FileOutputStream fos = new FileOutputStream("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/02.jpg");
        fos.write(imageData);
        fos.close();

        // 序列化和反序列化
        User user = new User("Tom", "123456");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.obj"));
        oos.writeObject(user);
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.obj"));
        User newUser = (User) ois.readObject();
        ois.close();
        System.out.println(newUser.getName() + " " + newUser.getPassword());

        // 网络编程和多线程 I/O
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(new ServerHandler(socket)).start();
        }
    }
}

class ServerHandler implements Runnable {
    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Client: " + line);
                writer.write("Server: " + line + "\n");
                writer.flush();
            }
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
