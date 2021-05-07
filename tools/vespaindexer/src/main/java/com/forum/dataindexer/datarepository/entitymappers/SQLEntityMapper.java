package com.forum.dataindexer.datarepository.entitymappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.forum.dataindexer.entitities.Entity;

public interface SQLEntityMapper<T extends Entity> {
    public String getSQLStatementForEntity();
    public T getEntityFromPreparedStatement(ResultSet rs) throws SQLException;
}
