package com.example.Fiizzle.src.subject;

import com.example.Fiizzle.config.BaseException;
import com.example.Fiizzle.config.BaseResponse;
import com.example.Fiizzle.src.subject.model.GetEachSubjectRes;
import com.example.Fiizzle.src.subject.model.GetSubjectRes;
import com.example.Fiizzle.src.subject.model.PostSubjectReq;
import com.example.Fiizzle.src.subject.model.PostSubjectRes;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {


    @Autowired
    private final SubjectProvider subjectProvider;
    @Autowired
    private final SubjectService subjectService;

    public SubjectController(SubjectProvider subjectProvider, SubjectService subjectService){
        this.subjectProvider = subjectProvider;
        this.subjectService = subjectService;
    }

    /**
     * 학습 계획 추가 API
     * [POST] /subject
     * @return PostSubjectRes
     */
    @ResponseBody
    @PostMapping("")
    @ApiOperation(value = "학습 계획 추가")
    @ApiResponses({
            @ApiResponse(code=4000, message="데이터베이스 연결에 실패하였습니다.")
    })
    public BaseResponse<PostSubjectRes> createSubject(@RequestBody PostSubjectReq postSubjectReq) {
        try{
            PostSubjectRes postSubjectRes = subjectService.createSubject(postSubjectReq);
            return new BaseResponse<>(postSubjectRes);
        } catch(BaseException exception){
            System.out.println(exception);
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    /**
     * 전체 학습 목록 API
     * [GET] /subject/info/all/1
     * @return List<GetSubjectRes>
     */
    @ResponseBody
    @GetMapping("/info/all/{userId}")  // (get) https://rina-server.shop/subject/info/all/1
    @ApiOperation(value = "전체 학습 목록 조회")
    @ApiImplicitParam(name="userId", value="조회할 유저의 ID", required = true)
    @ApiResponses({
            @ApiResponse(code=2010, message="유저 아이디 값을 확인해주세요."),
            @ApiResponse(code=4000, message="데이터베이스 연결에 실패하였습니다.")
    })
    public BaseResponse<List<GetSubjectRes>> getSubject(@PathVariable("userId") int userId){
        try{
            List<GetSubjectRes> getSubject = subjectProvider.findAllSubject(userId);
            return new BaseResponse<>(getSubject);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 개별 학습 계획 API
     * [GET] /subject/info/each/3
     * @return GetEachSubjectRes
     */
    @ResponseBody
    @GetMapping("/info/each/{subjectId}")  // (get) https://rina-server.shop/subject/info/each/6
    @ApiOperation(value = "개별 학습 계획")
    @ApiImplicitParam(name="subjectId", value="조회할 과목의 ID", required = true)
    @ApiResponses({
            @ApiResponse(code=4000, message="데이터베이스 연결에 실패하였습니다.")
    })
    public BaseResponse<GetEachSubjectRes> getEachSubject(@PathVariable("subjectId") int subjectId){
        try{
            GetEachSubjectRes getEachSubject = subjectProvider.findEachSubject(subjectId);
            return new BaseResponse<>(getEachSubject);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }










}
