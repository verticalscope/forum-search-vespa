package com.forum.dataindexer.entitities;

import java.sql.Timestamp;

public class Comment extends Entity{
    private int id;
    private int postId;
    private int score;
    private long creationDate;
    private int userId;
    private String text;

    public static class Builder {
        private int id;
        private int postId;
        private int score;
        private long creationDate;
        private int userId;
        private String text;
        
        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setPostId(int postId) {
            this.postId = postId;
            return this;
        }

        public Builder setScore(int score) {
            this.score = score;
            return this;
        }

        public Builder setCreationDate(Timestamp timestamp) {
            this.creationDate = timestamp.getTime();
            return this;
        }

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Comment build() {
            Comment comment = new Comment();
            comment.id = id;
            comment.postId = postId;
            comment.score = score;
            comment.creationDate = creationDate;
            comment.userId = userId;
            comment.text = text;
            return comment;
        }
    }

    private Comment() {

    }

    public int getId() {
        return id;
    }

    public int getPostId() {
        return postId;
    }

    public int getScore() {
        return score;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public int getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }
}
