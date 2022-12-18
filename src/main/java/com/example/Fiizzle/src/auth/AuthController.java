package com.example.Fiizzle.src.auth;

import com.example.Fiizzle.config.BaseException;
import com.example.Fiizzle.config.BaseResponse;
import com.example.Fiizzle.src.auth.model.PostRegisterReq;
import com.example.Fiizzle.src.auth.model.PostRegisterRes;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 회원가입
    @ResponseBody
    @ApiOperation(value = "회원 가입")
    @PostMapping("/register")
    public BaseResponse<PostRegisterRes> registerUser(@RequestBody PostRegisterReq postRegisterReq) {
        try {
            PostRegisterRes postRegisterRes = authService.registerUser(postRegisterReq);
            return new BaseResponse<>(postRegisterRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
