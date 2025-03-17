package com.bookmyshowspring.demo.models.user;


import com.bookmyshowspring.demo.enums.UserType;
import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "creator")
@TypeAlias("creator")
@EqualsAndHashCode(callSuper = true)
public class Creator extends User {
    private ArrayList<String> theatresOwned= new ArrayList<>();


    public Creator(String id, String name, String email, String phoneNumber) {
        super(id, name, email, phoneNumber, UserType.CREATOR);

    }

    public Creator(String id, String name, String email, String phoneNumber ,ArrayList<String> theatresOwned) {

        super(id, name, email, phoneNumber, UserType.CREATOR);
        this.theatresOwned=theatresOwned;
    }

    public Creator() {
        super();

    }

    public void setTheatresOwned(String thid) {
        this.theatresOwned.add(thid);
    }


    public ArrayList<String> getTheatresOwned() {
        return theatresOwned;
    }
}
