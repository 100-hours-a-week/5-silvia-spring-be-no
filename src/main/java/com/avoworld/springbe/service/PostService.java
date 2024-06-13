package com.avoworld.springbe.service;

import com.avoworld.springbe.exception.PostNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.avoworld.springbe.model.Post;
import com.avoworld.springbe.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);


    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long postId) {
        logger.debug("Fetching post with ID: {}", postId);
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new PostNotFoundException(postId); // 수정: 예외 발생
        }
    }

    public Post createPost(Post post) {
        post.setCreateAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post postDetails) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            post.setTitle(postDetails.getTitle());
            post.setArticle(postDetails.getArticle());
            post.setPostPicture(postDetails.getPostPicture());
            post.setUpdateAt(LocalDateTime.now());
            return postRepository.save(post);
        }
        return null;
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public void incrementViews(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            post.setViews(post.getViews() + 1);
            postRepository.save(post);
        }
    }

    public boolean hasEditPermission(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElse(null);
        return post != null && post.getUserId().equals(userId);
    }
}