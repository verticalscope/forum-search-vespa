package com.forum.dataindexer.datarepository;

import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.forum.dataindexer.datarepository.entitymappers.CommentSQLMapper;
import com.forum.dataindexer.datarepository.entitymappers.PostSQLMapper;
import com.forum.dataindexer.entities.Comment;
import com.forum.dataindexer.entities.Post;

public class SQLRepository implements Repository, AutoCloseable {
    private static final Logger log = Logger.getLogger(SQLRepository.class.getCanonicalName());
    private Connection dbConnection;
    private CommentSQLMapper commentSQLMapper = new CommentSQLMapper();
    private PostSQLMapper postSQLMapper = new PostSQLMapper();

    public SQLRepository(
        String node, 
        int port, 
        String dbName, 
        String user, 
        String password
    ) throws SQLException {
        String connectionString = String
            .format("jdbc:mysql://%s:%s/%s", node, port, dbName);
        this.dbConnection = DriverManager
            .getConnection(connectionString, user, password);
    }
    
    @Override
    public ArrayList<Comment> getComments() throws SQLException {
        ArrayList<Comment> comments = new ArrayList<>();
        String sqlQuery = commentSQLMapper.getSQLStatementForEntity();
        
        PreparedStatement ps = this.dbConnection.prepareStatement(sqlQuery);
        try {
            long startTimeMillis = System.currentTimeMillis();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                try {
                    comments.add(commentSQLMapper.getEntityFromPreparedStatement(rs));
                } catch (SQLException exception) {
                    log.warning("SQL Exception while trying to get a comment for ID:" +rs.getString(1) + " Message:" + exception.getMessage());
                }
                
            }
            long totalTimeMillis = System.currentTimeMillis() - startTimeMillis;
            
            log.info("Fetched " + comments.size() + "items in " + totalTimeMillis + " ms");
        } finally {
            ps.close();
        }
        
        return comments;
    }

    @Override
    public ArrayList<Post> getPosts() throws SQLException {
        ArrayList<Post> posts = new ArrayList<>();
        String sqlQuery = postSQLMapper.getSQLStatementForEntity();

        PreparedStatement ps = this.dbConnection.prepareStatement(sqlQuery);
        try {
            long startTimeMillis = System.currentTimeMillis();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                try {
                    posts.add(postSQLMapper.getEntityFromPreparedStatement(rs));
                } catch (SQLException exception) {
                    log.warning("SQL Exception while trying to get a post for ID:" +rs.getString(1) + " Message:" + exception.getMessage());
                }
            }
            long totalTimeMillis = System.currentTimeMillis() - startTimeMillis;
            
            log.info("Fetched " + posts.size() + "items in " + totalTimeMillis + " ms");
        } finally {
            ps.close();
        }

        return posts;
    }

    @Override
    public void close() throws Exception {
        if (!this.dbConnection.isClosed()) {
            this.dbConnection.close();
        }
    }
}
