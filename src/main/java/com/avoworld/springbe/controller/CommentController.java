package com.avoworld.springbe.controller;

import com.avoworld.springbe.model.Comment;
import com.avoworld.springbe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/posts/{postId}/comments")
    public List<Comment> getCommentsByPostId(@PathVariable("postId") Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/comments")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public Comment getCommentById(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId) {
        return commentService.getCommentById(commentId);
    }

    @PostMapping("/posts/{postId}/comments")
    public Comment createComment(@PathVariable("postId") Long postId, @RequestBody Comment comment) {
        comment.setPostId(postId);
        return commentService.createComment(comment);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @RequestBody Comment commentDetails) {
        commentDetails.setPostId(postId);
        return commentService.updateComment(commentId, commentDetails);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
    }
}