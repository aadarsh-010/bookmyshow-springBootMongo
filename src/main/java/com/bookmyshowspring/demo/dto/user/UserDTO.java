package com.bookmyshowspring.demo.dto.user;


import com.bookmyshowspring.demo.enums.UserType;

public class UserDTO {

    protected String userid;
    protected String name;
    protected String email;
    protected String phoneNumber;
    protected UserType usertype;


    public UserDTO(String userid, String name, String email, String phoneNumber, UserType usertype) {
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.usertype = usertype;
    }
    public UserDTO(){}

    public UserType getUsertype() {
        return usertype;
    }

    public void setUsertype(UserType usertype) {
        this.usertype = usertype;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
