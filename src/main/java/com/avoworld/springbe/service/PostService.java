package com.avoworld.springbe.service;

import com.avoworld.springbe.model.Post;
import com.avoworld.springbe.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post postDetails) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            post.setTitle(postDetails.getTitle());
            post.setArticle(postDetails.getArticle());
            post.setPostPicture(postDetails.getPostPicture());
            post.setUpdateAt(postDetails.getUpdateAt());
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
}