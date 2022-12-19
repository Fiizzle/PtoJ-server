package com.example.Fiizzle.src.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostRegisterReq {
    private String email;
    private String nickName;
    private String password;
}
