package com.forum.dataindexer.datarepository;

import java.sql.SQLException;
import java.util.ArrayList;

import com.forum.dataindexer.entities.Comment;
import com.forum.dataindexer.entities.Post;

public interface Repository {
    default ArrayList<Comment> getComments() throws SQLException, UnsupportedOperationException {
        throw new UnsupportedOperationException("Method not implementeda");
    }

    default ArrayList<Post> getPosts() throws SQLException, UnsupportedOperationException {
        throw new UnsupportedOperationException("Method not implementeda");
    }

    default void batchFeedComments(ArrayList<Comment> records) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Method not implementeda");
    }

    default void batchFeedPosts(ArrayList<Post> records) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Method not implementeda");
    }
}
