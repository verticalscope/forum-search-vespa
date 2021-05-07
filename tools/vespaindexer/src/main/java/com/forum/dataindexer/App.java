package com.forum.dataindexer;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.forum.dataindexer.datarepository.SQLRepository;
import com.forum.dataindexer.datarepository.VespaRepository;
import com.forum.dataindexer.entities.Comment;
import com.forum.dataindexer.entities.Post;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger log = Logger.getLogger(SQLRepository.class.getCanonicalName());

    // Predefined settings
    private static final String SQL_NODE = "localhost";
    private static final int SQL_PORT = 3306;
    private static final String SQL_DB = "forum_data";
    private static final String SQL_USER = "root";
    private static final String SQL_PASSWORD = "vespamysql";
    private static final String VESPA_HOST = "localhost";
    private static final int VESPA_PORT = 8080;
    // Predefined settings section ends

    public static void main( String[] args ) throws Exception
    {
        try(SQLRepository sqlRepository = 
            new SQLRepository(SQL_NODE, SQL_PORT, SQL_DB, SQL_USER, SQL_PASSWORD)) {
            ArrayList<Comment> sqlComments = sqlRepository.getComments();
            log.info("SQL comments size:"+ sqlComments.size());
            ArrayList<Post> sqlPosts = sqlRepository.getPosts();
            log.info("SQL posts size:"+ sqlPosts.size());
            try(VespaRepository vespaRepository = new VespaRepository(VESPA_HOST, VESPA_PORT)) {
                vespaRepository.batchFeedComments(sqlComments);
                vespaRepository.batchFeedPosts(sqlPosts);
            }
        }
    }
}
