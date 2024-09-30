package com.michael.test.controller.dto.response;

import com.michael.test.model.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private Date birth;
    private String gender;
    private String address;

    public UserResponse(Users users) {
        this.firstName = users.getFirstName();
        this.lastName = users.getLastName();
        this.email = users.getEmail();
        this.birth = users.getBirth();
        this.gender = users.getGender();
        this.address = users.getAddress();
    }
}
