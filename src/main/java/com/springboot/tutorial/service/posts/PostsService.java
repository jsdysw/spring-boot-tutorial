package com.springboot.tutorial.service.posts;

import com.springboot.tutorial.api.dto.PostsResponseDto;
import com.springboot.tutorial.api.dto.PostsSaveRequestDto;
import com.springboot.tutorial.api.dto.PostsUpdateRequestDto;
import com.springboot.tutorial.domain.posts.Posts;
import com.springboot.tutorial.domain.posts.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user found. id = "+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user found. id = "+id));
        return new PostsResponseDto(entity);
    }

}
