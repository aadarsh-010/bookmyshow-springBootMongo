package com.bookmyshowspring.demo.dto;



import com.bookmyshowspring.demo.models.Show;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreenDTO {


    private String id;
    private String theatreid;
    private String screenSeatID;
    private ArrayList<String> showRef;


}

