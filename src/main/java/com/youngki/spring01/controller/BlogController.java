package com.youngki.spring01.controller;


import com.youngki.spring01.domain.BlogRepository;
import com.youngki.spring01.domain.dto.*;
import com.youngki.spring01.service.BlogPwService;
import com.youngki.spring01.service.BlogService;
import com.youngki.spring01.service.FreeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BlogController {
    private final BlogRepository blogRepository;
    private final BlogService blogService;
    private final BlogPwService blogPwService;
    private final FreeBoardRepository freeBoardRepository;


    @GetMapping("/api/blogs")
    public ApiResult<?> getBlog(){
        return ApiUtils.success(blogRepository.findAllByOrderByModifiedAtDesc());
    }

    @GetMapping("/api/blogs/{id}")
    public ApiResult<?> getBlogId(@PathVariable Long id){
        return ApiUtils.success(blogRepository.findById(id));
    }

    @PostMapping("/api/blogs/post")
    public ApiResult<?> posrBlog(@RequestBody BlogRequestDto requestDto){
        return ApiUtils.success(blogRepository.findById(blogPwService.Pw(requestDto)));
    }

//    @PostMapping("/api/posts/{id}")
//    public ApiResult<Boolean> postBlogId(@RequestParam("password") String password, @PathVariable Long id){
//        boolean result = BlogPwService.checkPassword(id,password);
//        return ApiUtils.success(rusult);
//    }
    @PostMapping("/api/blogs/post/{id}")
    public Boolean passwordBlog(@PathVariable Long id, @RequestBody PasswordDto requestDto) {
        return freeBoardRepository.confirm(id, requestDto);
    }

    @PatchMapping("/api/blogs/{id}")
    public ApiResult<?> putBlog(@PathVariable Long id, @RequestBody PasswordDto requestDto){
        if(freeBoardRepository.confirm(id, requestDto)) {
            return ApiUtils.success(blogService.update(id,requestDto));
        }else{
            return ApiUtils.success(false);
        }
    }

    @DeleteMapping("/api/blogs/{id}")
    public ApiResult<Boolean> deleteBlog( @PathVariable Long id, @RequestBody PasswordDto requestDto){
        if(freeBoardRepository.confirm(id, requestDto)){
            blogRepository.deleteById(id);
            return ApiUtils.success(true);
        }else {
            return ApiUtils.success(false);
        }
    }
}

