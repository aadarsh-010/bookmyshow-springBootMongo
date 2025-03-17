package com.bookmyshowspring.demo.models.user;


import com.bookmyshowspring.demo.enums.UserType;
import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
@Setter
@Getter
@Document(collection = "customer")
@TypeAlias("customer")
@EqualsAndHashCode(callSuper = true)
public class Customer extends User {
    private ArrayList<String> userBookings = new ArrayList<>();


    public Customer(String id, String name, String email, String phoneNumber) {
        super(id, name, email, phoneNumber, UserType.CUSTOMER);
    }
    public Customer(String id, String name, String email, String phoneNumber ,ArrayList<String> userBookings) {

        super(id, name, email, phoneNumber, UserType.CUSTOMER);
        this.userBookings=userBookings;
    }


    public Customer() {
        super();
    }


    public void setUserBookings(String bid) {
        this.userBookings.add(bid);
    }

    public ArrayList<String> getUserBookings() {
        System.out.println("hello");
        return userBookings;
    }
}
