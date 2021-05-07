package com.forum.dataindexer.datarepository.entitymappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.forum.dataindexer.entities.Post;

public class PostSQLMapper implements SQLEntityMapper<Post>{
    private static final String SQL_TABLE_NAME = "Posts";
    private static final String ID_COLUMN = "Id";
    private static final String PARENT_ID_COLUMN = "ParentId";
    private static final String ACCEPTED_ANSWER_ID_COLUMN = "AcceptedAnswerId";
    private static final String SCORE_COLUMN = "Score";
    private static final String CREATION_DATE_COLUMN = "CreationDate";
    private static final String OWNER_USER_ID_COLUMN = "OwnerUserId";
    private static final String BODY_COLUMN = "Body";
    private static final String TITLE_COLUMN = "Title";

    @Override
    public String getSQLStatementForEntity() {
        return "SELECT * FROM "+ SQL_TABLE_NAME + ";";
    }

    @Override
    public Post getEntityFromPreparedStatement(ResultSet rs) throws SQLException {
        Post.Builder postBuilder = new Post.Builder()
            .setId(rs.getInt(ID_COLUMN))
            .setParentId(rs.getInt(PARENT_ID_COLUMN))
            .setAcceptedAnserId(rs.getInt(ACCEPTED_ANSWER_ID_COLUMN))
            .setScore(rs.getInt(SCORE_COLUMN))
            .setCreationDate(rs.getTimestamp(CREATION_DATE_COLUMN))
            .setOwnerUserId(rs.getInt(OWNER_USER_ID_COLUMN))
            .setBody(rs.getString(BODY_COLUMN))
            .setTitle(rs.getString(TITLE_COLUMN));
        return postBuilder.build();
    }
    
}
