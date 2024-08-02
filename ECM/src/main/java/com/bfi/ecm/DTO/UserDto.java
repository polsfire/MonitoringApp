package com.bfi.ecm.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/*
ddto = =data transfer object

* */
public class UserDto {


    private Long id;
    private String username;
    private String firstname;
    private String Lastname;
    private String email;
    private String password;
    private String token;
    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                // include other fields
                '}';
    }


}
