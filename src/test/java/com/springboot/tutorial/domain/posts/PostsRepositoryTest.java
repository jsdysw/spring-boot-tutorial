package com.springboot.tutorial.domain.posts;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After(value = "savePosts_load()")
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void savePosts_load() {
        //given
        String title = "testpost";
        String content = "testcontent";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jsdysw@jsdysw")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
