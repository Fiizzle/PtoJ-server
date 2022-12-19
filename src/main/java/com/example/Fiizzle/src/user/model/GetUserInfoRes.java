package com.example.Fiizzle.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserInfoRes {

    private String nickname;
    private String email;
    private int done;
    private int ing;



}
