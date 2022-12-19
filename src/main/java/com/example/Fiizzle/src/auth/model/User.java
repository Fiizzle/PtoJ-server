package com.example.Fiizzle.src.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private int userIdx;
    private String nickName;
    private String email;
    private String password;
    private int status;
}