package com.avoworld.springbe.controller;

import com.avoworld.springbe.model.Post;
import com.avoworld.springbe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody Post postDetails) {
        return postService.updatePost(postId, postDetails);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }

    @PutMapping("/{postId}/views")
    public void incrementViews(@PathVariable Long postId) {
        postService.incrementViews(postId);
    }

    @PostMapping("/image")
    public String uploadPostImage(@RequestParam("postImage") MultipartFile file) {
        if (file.isEmpty()) {
            return "No file uploaded";
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("./uploads/" + file.getOriginalFilename());
            Files.write(path, bytes);
            return path.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload";
        }
    }
}