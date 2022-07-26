package com.youngki.spring01.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngki.spring01.domain.dto.BlogRequestDto;
import com.youngki.spring01.domain.dto.PasswordDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Blog extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    public Blog(BlogRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }

    public Blog(String title,String username, String contents, String password) {
        this.title = title;
        this.username = username;
        this.contents = contents;
        this.password = password;
    }

    public void update(PasswordDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }

    public boolean confirm(PasswordDto requestDto){
        boolean checkbool = this.password.equals(requestDto.getPassword());
        return checkbool;
    }
}
