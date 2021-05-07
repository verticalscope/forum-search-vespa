package com.forum.dataindexer.datarepository.entitymappers;

import com.forum.dataindexer.entities.Entity;

import org.json.JSONObject;

public interface VespaEntityMapper<T extends Entity> {
    public static final String VESPA_NAMESPACE = "diy";
    public String getVespaEntityString();
    public JSONObject getVespaFeedRequest(T record);
}
