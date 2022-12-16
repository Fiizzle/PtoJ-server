package com.example.Fiizzle.src.subject;

import com.example.Fiizzle.config.BaseException;
import com.example.Fiizzle.config.BaseResponse;
import com.example.Fiizzle.src.subject.model.PostSubjectReq;
import com.example.Fiizzle.src.subject.model.PostSubjectRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subject")
public class SubjectController {


    @Autowired
    private final SubjectProvider subjectProvider;
    @Autowired
    private final SubjectService subjectService;
//    @Autowired
//    private final JwtService jwtService;

    public SubjectController(SubjectProvider subjectProvider, SubjectService subjectService){
        this.subjectProvider = subjectProvider;
        this.subjectService = subjectService;
    }

//    @ResponseBody
//    @PostMapping("")
//    public BaseResponse<PostPostRes> createPost(@RequestBody PostPostReq postPostReq) {
//        if(postPostReq.getContent() == null){
//            return new BaseResponse<>(POST_POSTS_INVALID_CONTENTS);
//        }
//        if(postPostReq.getContent().length()>450){
//            return new BaseResponse<>(POST_POSTS_INVALID_CONTENTS);
//        }
//
//        try{
//            int userIdxByJwt = jwtService.getUserIdx();
//            PostPostRes postPostRes = postService.createPost(userIdxByJwt,postPostReq);
//            return new BaseResponse<>(postPostRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostSubjectRes> createSubject(@RequestBody PostSubjectReq postSubjectReq) {
        try{
            PostSubjectRes postSubjectRes = subjectService.createSubject(postSubjectReq);
            return new BaseResponse<>(postSubjectRes);
        } catch(BaseException exception){
            System.out.println(exception);
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
