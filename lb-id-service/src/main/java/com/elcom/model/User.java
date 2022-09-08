package com.elcom.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long  serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "ID tự tăng")
    private int id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "fullname")
    private String fullName;

    @Column(name ="email")
    private String email;

    @Column(name ="mobile")
    private int mobile;

    @Column(name = "address")
    private String address;


    public User()
    {}
    public User(int id,String userName,String password,String roleName,String fullName,String email,int mobile,String address)
    {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roleName = roleName;
        this.fullName = fullName;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }
    public int getId()
    {
        return id;
    }
    public void setId( int id)
    {
        this.id = id;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getFullName()
    {
        return fullName;
    }
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getMobile()
    {
        return mobile;
    }
    public void setMobile(int mobile)
    {
        this.mobile = mobile;
    }

    public String getAddress()
    {
        return address;
    }
    public void setAddress(String getAddress)
    {
        this.address = address;
    }
}
