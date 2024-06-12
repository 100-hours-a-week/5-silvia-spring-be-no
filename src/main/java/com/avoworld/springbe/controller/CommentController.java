package com.avoworld.springbe.controller;

import com.avoworld.springbe.model.Comment;
import com.avoworld.springbe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Comment> getCommentsByPostId(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/{commentId}")
    public Comment getCommentById(@PathVariable Long postId, @PathVariable Long commentId) {
        return commentService.getCommentById(commentId);
    }

    @PostMapping
    public Comment createComment(@PathVariable Long postId, @RequestBody Comment comment) {
        comment.setPostId(postId);
        return commentService.createComment(comment);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody Comment commentDetails) {
        return commentService.updateComment(commentId, commentDetails);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}