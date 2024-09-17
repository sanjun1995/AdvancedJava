package com.example.demo.b2c.mapstruct;

/**
 * @author caozhixin
 * @date 2024/9/17 14:57
 */
public class MapStructDemo {
    public static void main(String[] args) {
        // 创建 UserDTO 对象
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setEmail("john.doe@example.com");

        // 使用 MapStruct 进行对象转换
        User user = UserMapper.INSTANCE.toEntity(userDTO);

        // 打印转换后的 User 对象
        System.out.println("User Entity: " + user);

        // 反向转换 User 为 UserDTO
        UserDTO newUserDTO = UserMapper.INSTANCE.toDTO(user);
        System.out.println("UserDTO: " + newUserDTO);
    }
}
