package com.youngki.spring01.service;

import com.youngki.spring01.domain.Blog;
import com.youngki.spring01.domain.BlogRepository;
import com.youngki.spring01.domain.dto.BlogRequestDto;
import com.youngki.spring01.domain.dto.PasswordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class BlogPwService {
    private final BlogRepository blogRepository;

    @Autowired
    public BlogPwService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Long Pw(BlogRequestDto requestDto) {
        String username = requestDto.getUsername();
        String title = requestDto.getTitle();
        String contents = requestDto.getContents();
        String password = requestDto.getPassword();

        Blog post= new Blog(username, title, contents, password);
        blogRepository.save(post);
        return post.getId();
    }

    public boolean checkPassword(Long id, String password) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("id가 없습니다.")
        );
        return blog.getPassword().equals(password);
    }

    @Transactional
    public Boolean confirm(Long id, PasswordDto requestDto){
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return blog.confirm(requestDto);
    }
}
