package cn.edu.nchu.software.entity;

import lombok.Data;

/**
 * @ClassName UserEntity
 * @Description TODO
 * @Auther 范孝发
 * @Date 2019/5/2 17:05
 * @Version 1.0
 */

@Data
public class UserEntity {
    private Integer userID;
    private String name;
    private String username;
    private String password;
    private Integer userType;  //1-管理员；2-教师；3-学生
    private String email;
    private String picture;
    private Integer state;    //0锁，1无锁
    private String remarks;
}
