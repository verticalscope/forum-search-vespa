package com.forum.dataindexer.entities;

import java.sql.Timestamp;

public class Post extends Entity {
    private int id;
    private int parentId;
    private int acceptedAnserId;
    private int score;
    private long creationDate;
    private int ownerUserId;
    private String body;
    private String title;

    private Post() {}

    public int getParentId() {
        return parentId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public int getOwnerUserId() {
        return ownerUserId;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public int getScore() {
        return score;
    }

    public int getAcceptedAnserId() {
        return acceptedAnserId;
    }

    public static class Builder {
        private int id;
        private int parentId;
        private int acceptedAnserId;
        private int score;
        private long creationDate;
        private int ownerUserId;
        private String body;
        private String title;

        public Builder setAcceptedAnserId(int acceptedAnserId) {
            this.acceptedAnserId = acceptedAnserId;
            return this;
        }

        public Builder setScore(int score) {
            this.score = score;
            return this;
        }

        public Builder setCreationDate(Timestamp creationDate) {
            this.creationDate = creationDate.getTime();
            return this;
        }

        public Builder setOwnerUserId(int ownerUserId) {
            this.ownerUserId = ownerUserId;
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setParentId(int parentId) {
            this.parentId = parentId;
            return this;
        }

        public Post build() {
            Post post = new Post();
            post.id = id;
            post.parentId = parentId;
            post.acceptedAnserId = acceptedAnserId;
            post.score = score;
            post.creationDate = creationDate;
            post.ownerUserId = ownerUserId;
            post.body = body;
            post.title = title;
            return post;
        }


    }
}
