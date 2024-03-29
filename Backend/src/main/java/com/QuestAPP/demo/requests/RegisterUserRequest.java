package com.QuestAPP.demo.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RegisterUserRequest {

    private String firstname;
    private String lastname;
    private String password;
    private String email;

}
