package com.avoworld.springbe.service;

import com.avoworld.springbe.model.Comment;
import com.avoworld.springbe.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long id, Comment commentDetails) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment != null) {
            comment.setCommentContent(commentDetails.getCommentContent());
            comment.setUpdateAt(commentDetails.getUpdateAt());
            return commentRepository.save(comment);
        }
        return null;
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
