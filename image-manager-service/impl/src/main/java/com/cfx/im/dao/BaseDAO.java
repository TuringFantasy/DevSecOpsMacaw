package com.cfx.im.dao;

import java.util.List;

import com.cfx.im.db.DBCluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.MappingManager;

public abstract class BaseDAO<T> {
    private final DBCluster cluster = DBCluster.getInstance();

    public abstract String getCFName();

    protected abstract List<T> getDtosFromResultSet(ResultSet resultSet);

    protected Session getSession() {
        return cluster.getSession();
    }

    protected MappingManager getMappingManager() {
        return cluster.getMappingManager();
    }

    public List<T> findAll() {
        final Statement statement = QueryBuilder.select().all().from(getCFName());
        return getDtosFromResultSet(getSession().execute(statement));
    }

    public List<T> findByQuery(final String query) {
        final Statement statement = new SimpleStatement(query);
        return getDtosFromResultSet(getSession().execute(statement));
    }

    protected boolean isNullorEmpty(final String s) {
        return s == null || s.trim().length() == 0;
    }
}
