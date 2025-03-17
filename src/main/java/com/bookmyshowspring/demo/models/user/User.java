package com.bookmyshowspring.demo.models.user;


import com.bookmyshowspring.demo.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
@TypeAlias("user")
public class User{

    @Id
    protected String userid;
    protected String name;
    protected String email;
    protected String phoneNumber;
    protected UserType usertype;

    public User(String userid, String name, String email, String phoneNumber, UserType usertype) {
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.usertype = usertype;
    }
    public User(){}

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

    public UserType getUsertype() {
        return usertype;
    }

    public void setUsertype(UserType usertype) {
        this.usertype = usertype;
    }
}
