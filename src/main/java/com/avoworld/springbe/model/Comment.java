package com.avoworld.springbe.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post_comment")
public class Comment {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public String getEditedCommentContent() {
        return editedCommentContent;
    }

    public void setEditedCommentContent(String editedCommentContent) {
        this.editedCommentContent = editedCommentContent;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;
    private Long userId;
    private String commentContent;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String editedCommentContent;

}
