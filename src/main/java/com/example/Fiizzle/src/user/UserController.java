package com.example.Fiizzle.src.user;

import com.example.Fiizzle.config.BaseException;
import com.example.Fiizzle.config.BaseResponse;
import com.example.Fiizzle.src.user.model.GetUserInfoRes;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {



    @Autowired
    private final UserProvider userProvider;

    public UserController(UserProvider userProvider){
        this.userProvider = userProvider;
    }

    /**
     * 마이페이지 API
     * [GET] /user/info/1
     * @return List<GetUserInfoRes>
     */
    @ResponseBody
    @GetMapping("/info/{userId}")  // (get) https://rina-server.shop/user/info/1
    @ApiOperation(value = "마이페이지 조회")
    @ApiImplicitParam(name="userId", value="조회할 유저의 ID", required = true)
    @ApiResponses({
            @ApiResponse(code=2010, message="유저 아이디 값을 확인해주세요."),
            @ApiResponse(code=4000, message="데이터베이스 연결에 실패하였습니다.")
    })
    public BaseResponse<GetUserInfoRes> getUser(@PathVariable("userId") int userId){
        try{
            GetUserInfoRes getUser = userProvider.findUserInfo(userId);
            return new BaseResponse<>(getUser);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }



}
