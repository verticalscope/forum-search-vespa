package com.forum.dataindexer.datarepository.entitymappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.forum.dataindexer.entities.Entity;

public interface SQLEntityMapper<T extends Entity> {
    public String getSQLStatementForEntity();
    public T getEntityFromPreparedStatement(ResultSet rs) throws SQLException;
}
