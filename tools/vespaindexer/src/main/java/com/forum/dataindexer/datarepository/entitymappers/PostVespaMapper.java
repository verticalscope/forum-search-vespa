package com.forum.dataindexer.datarepository.entitymappers;

import com.forum.dataindexer.datarepository.entitymappers.utils.Utils;
import com.forum.dataindexer.entitities.Post;

import org.json.JSONObject;

public class PostVespaMapper implements VespaEntityMapper<Post>{
    private static final String VESPA_ENTITY_KEY = "post";

    @Override
    public JSONObject getVespaFeedRequest(Post record) {
        return new JSONObject()
            .put(
                "update", 
                "id:" + VESPA_NAMESPACE + 
                    ":" + getVespaEntityString() + "::" + record.getId()
            )
            .put("create", true)
            .put("fields", new JSONObject()
                .put("Id", Utils.vespaAssign(record.getId()))
                .put("ParentId", Utils.vespaAssign(record.getParentId()))
                .put("AcceptedAnswerId", Utils.vespaAssign(record.getAcceptedAnserId()))
                .put("Score", Utils.vespaAssign(record.getScore()))
                .put("CreationDate", Utils.vespaAssign(record.getCreationDate()))
                .put("OwnerUserId", Utils.vespaAssign(record.getOwnerUserId()))
                .put("Body", Utils.vespaAssign(
                    Utils.vespaStripInvalidCharacters(record.getBody())
                ))
                .put("Title", Utils.vespaAssign(
                    Utils.vespaStripInvalidCharacters(record.getTitle())
                ))
            );
    }

    @Override
    public String getVespaEntityString() {
        return VESPA_ENTITY_KEY;
    }
}
