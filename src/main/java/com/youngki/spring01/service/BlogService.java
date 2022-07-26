package com.youngki.spring01.service;


import com.youngki.spring01.domain.Blog;
import com.youngki.spring01.domain.BlogRepository;
import com.youngki.spring01.domain.dto.BlogRequestDto;
import com.youngki.spring01.domain.dto.PasswordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    @Transactional
    public Long update(Long id, PasswordDto requestDto) {

            Blog blog = blogRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );

            blog.update(requestDto);
            return blog.getId();

    }
}
