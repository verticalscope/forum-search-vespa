package com.forum.dataindexer.datarepository.entitymappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.forum.dataindexer.entitities.Comment;

public class CommentSQLMapper implements SQLEntityMapper<Comment> {
    private static final String SQL_TABLE_NAME = "Comments";
    private static final String ID_COLUMN = "Id";
    private static final String POST_ID_COLUMN = "PostId";
    private static final String SCORE_COLUMN = "Score";
    private static final String CREATION_DATE_COLUMN = "CreationDate";
    private static final String USER_ID_COLUMN = "UserId";
    private static final String TEXT_COLUMN = "Text";

    @Override
    public Comment getEntityFromPreparedStatement(ResultSet rs) throws SQLException {
        Comment.Builder commentBuilder = new Comment.Builder()
            .setId(rs.getInt(ID_COLUMN))
            .setPostId(rs.getInt(POST_ID_COLUMN))
            .setScore(rs.getInt(SCORE_COLUMN))
            .setCreationDate(rs.getTimestamp(CREATION_DATE_COLUMN))
            .setUserId(rs.getInt(USER_ID_COLUMN))
            .setText(rs.getString(TEXT_COLUMN));
        return commentBuilder.build();
    }

    @Override
    public String getSQLStatementForEntity() {
        return "SELECT * FROM "+ SQL_TABLE_NAME + ";";
    }
    
}
