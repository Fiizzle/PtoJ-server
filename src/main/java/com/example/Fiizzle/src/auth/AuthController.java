package com.example.Fiizzle.src.auth;

import com.example.Fiizzle.config.BaseException;
import com.example.Fiizzle.config.BaseResponse;
import com.example.Fiizzle.src.auth.model.PostLoginReq;
import com.example.Fiizzle.src.auth.model.PostLoginRes;
import com.example.Fiizzle.src.auth.model.PostRegisterReq;
import com.example.Fiizzle.src.auth.model.PostRegisterRes;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.Fiizzle.config.BaseResponseStatus.POST_USERS_EMPTY_EMAIL;
import static com.example.Fiizzle.config.BaseResponseStatus.POST_USERS_EMPTY_PASSWORD;


@RestController
@RequestMapping("/auth")
public class AuthController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AuthService authService;
    private final AuthProvider authProvider;
    @Autowired
    public AuthController(AuthService authService, AuthProvider authProvider) {
        this.authService = authService;
        this.authProvider = authProvider;
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

    // 로그인
    @ResponseBody
    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public BaseResponse<PostLoginRes> login(@RequestBody PostLoginReq postLoginReq) {
        try {
            // 이메일 입력 공백
            if (postLoginReq.getEmail().isBlank()) {
                return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
            }
            // 비밀번호 입력 공백
            if (postLoginReq.getPassword().isBlank()) {
                return new BaseResponse<>(POST_USERS_EMPTY_PASSWORD);
            }
            PostLoginRes postLoginRes = authProvider.login(postLoginReq);

            return new BaseResponse<>(postLoginRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
