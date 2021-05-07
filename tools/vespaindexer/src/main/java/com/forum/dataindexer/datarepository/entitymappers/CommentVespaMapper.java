package com.forum.dataindexer.datarepository.entitymappers;

import com.forum.dataindexer.datarepository.entitymappers.utils.Utils;
import com.forum.dataindexer.entitities.Comment;

import org.json.JSONObject;

public class CommentVespaMapper implements VespaEntityMapper<Comment>{
    private static final String VESPA_ENTITY_KEY = "comment";

    @Override
    public JSONObject getVespaFeedRequest(Comment record) {
        return new JSONObject()
            .put(
                "update", 
                "id:" + VESPA_NAMESPACE + 
                    ":" + getVespaEntityString() + "::" + record.getId()
            )
            .put("create", true)
            .put("fields", new JSONObject()
                .put("Id", Utils.vespaAssign(record.getId()))
                .put("PostId", Utils.vespaAssign(record.getPostId()))
                .put("Score", Utils.vespaAssign(record.getScore()))
                .put("CreationDate", Utils.vespaAssign(record.getCreationDate()))
                .put("UserId", Utils.vespaAssign(record.getUserId()))
                .put("Text", Utils.vespaAssign(
                    Utils.vespaStripInvalidCharacters(record.getText())
                ))
            );
    }

    @Override
    public String getVespaEntityString() {
        return VESPA_ENTITY_KEY;
    }
}
