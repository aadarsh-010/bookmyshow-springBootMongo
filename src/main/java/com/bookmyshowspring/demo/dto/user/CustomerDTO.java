package com.bookmyshowspring.demo.dto.user;


import com.bookmyshowspring.demo.enums.UserType;

import java.util.ArrayList;
import java.util.Objects;

public class CustomerDTO extends UserDTO{
    private final ArrayList<String> userBookings = new ArrayList<>();


    public CustomerDTO(String id, String name, String email, String phoneNumber) {
        super(id, name, email, phoneNumber, UserType.CUSTOMER);
    }
    public CustomerDTO() {
        super();

    }


    public ArrayList<String> getUserBookings() {
        return userBookings;
    }


    public void setUserBookings(String bid) {
        this.userBookings.add(bid);
    }

}
