package com.forum.dataindexer.datarepository;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import com.forum.dataindexer.datarepository.entitymappers.CommentVespaMapper;
import com.forum.dataindexer.datarepository.entitymappers.PostVespaMapper;
import com.forum.dataindexer.datarepository.entitymappers.VespaEntityMapper;
import com.forum.dataindexer.entities.Comment;
import com.forum.dataindexer.entities.Entity;
import com.forum.dataindexer.entities.Post;
import com.yahoo.vespa.http.client.FeedClient;
import com.yahoo.vespa.http.client.FeedClientFactory;
import com.yahoo.vespa.http.client.SimpleLoggerResultCallback;
import com.yahoo.vespa.http.client.config.Cluster;
import com.yahoo.vespa.http.client.config.ConnectionParams;
import com.yahoo.vespa.http.client.config.Endpoint;
import com.yahoo.vespa.http.client.config.FeedParams;
import com.yahoo.vespa.http.client.config.SessionParams;

import org.json.JSONObject;

public class VespaRepository implements Repository, AutoCloseable {
    private final static Logger log = Logger.getLogger(VespaRepository.class.getCanonicalName());

    private final Endpoint endpoint;
    private final FeedClient feedClient;
    private final AtomicInteger numSent;
    private final SimpleLoggerResultCallback resultCallback;
    private final CommentVespaMapper commentVespaMapper = new CommentVespaMapper();
    private final PostVespaMapper postVespaMapper = new PostVespaMapper();

    public VespaRepository(String vespaHost, int port) {
        endpoint = Endpoint.create(vespaHost, port, false);
        var sessionParams = new SessionParams.Builder()
            .addCluster(new Cluster.Builder().addEndpoint(endpoint).build())
            .setConnectionParams(new ConnectionParams.Builder()
                .setUseCompression(true)
                .setMinTimeBetweenRetries(3, TimeUnit.SECONDS)
                .build())
            .setFeedParams(new FeedParams.Builder()
                .setDataFormat(FeedParams.DataFormat.JSON_UTF8)
                .setClientTimeout(60, TimeUnit.SECONDS)
                .setMaxSleepTimeMs(10000)
                .build()).build();
        numSent = new AtomicInteger(0);
        resultCallback = new SimpleLoggerResultCallback(this.numSent, 1000);
        feedClient = FeedClientFactory.create(sessionParams, resultCallback);
    }

    @Override
    public void batchFeedComments(ArrayList<Comment> records) throws UnsupportedOperationException {
        // ArrayList<JSONObject> vespaIndexObjects = new ArrayList<>();
        // for (Comment record: records) {
        //     vespaIndexObjects.add(commentVespaMapper.getVespaFeedRequest(record));
        // }
        // var json = vespaIndexObjects.toString();
        // var inputStream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        // feedVespa(inputStream);
        batchFeedHelper(records, commentVespaMapper);
    }

    @Override
    public void batchFeedPosts(ArrayList<Post> records) throws UnsupportedOperationException {
        batchFeedHelper(records, postVespaMapper);
    }

    private <T extends Entity> void batchFeedHelper(ArrayList<T> records, VespaEntityMapper<T> entityMapper) throws UnsupportedOperationException {
        ArrayList<JSONObject> vespaIndexObjects = new ArrayList<>();
        for (T record: records) {
            vespaIndexObjects.add(entityMapper.getVespaFeedRequest(record));
        }
        var json = vespaIndexObjects.toString();
        var inputStream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        feedVespa(inputStream);
    }

    private void feedVespa(InputStream inputStream) {
        FeedClient.feedJson(inputStream, feedClient, numSent);
        log.info("Total docs sent:" + numSent);
    }

    @Override
    public void close() throws Exception {
        feedClient.close();
        resultCallback.printProgress();
    }
}
