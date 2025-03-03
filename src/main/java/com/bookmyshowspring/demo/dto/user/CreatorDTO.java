package com.bookmyshowspring.demo.dto.user;


import com.bookmyshowspring.demo.enums.UserType;

import java.util.ArrayList;
import java.util.Objects;

public class CreatorDTO extends UserDTO {
    private final ArrayList<String> theatresOwned= new ArrayList<>();


    public CreatorDTO(String id, String name, String email, String phoneNumber) {
        super(id, name, email, phoneNumber, UserType.CREATOR);

    }
    public CreatorDTO(){}

    public ArrayList<String> getTheatresOwned() {
        return theatresOwned;
    }

    public void setTheatresOwned(String thid) {
        this.theatresOwned.add(thid);
    }


}
