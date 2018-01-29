package com.cfx.im.db;

import com.cfx.service.api.config.Configuration;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.DefaultPropertyMapper;
import com.datastax.driver.mapping.MappingConfiguration;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.PropertyAccessStrategy;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicBoolean;

public class DBCluster {

    private final AtomicBoolean initDone = new AtomicBoolean(false);

    private DBConfig dbconfig;
    private Cluster cluster;
    private Session session;
    private MappingManager mappingManager;

    private DBCluster() {
    }

    private static final DBCluster _instance = new DBCluster();

    public static DBCluster getInstance() {
        return _instance;
    }

    public void init(final Configuration configuration) throws IOException {
        if (initDone.get()) {
            return;
        }

        this.dbconfig = new DBConfig().loadFromConfiguration(configuration);

        innerInit();

        initDone.set(true);
    }

    public void innerInit() throws IOException {
        final Builder bldr = Cluster.builder().withProtocolVersion(ProtocolVersion.V3);
        for (final InetSocketAddress node : this.dbconfig.getAddresses()) {
            if (node == null) {
                continue;
            }
            bldr.addContactPointsWithPorts(node);
        }

        cluster = bldr.withCredentials(this.dbconfig.getUsername(), this.dbconfig.getPassword()).build();

        // Connect to the keyspace
        session = cluster.connect(this.dbconfig.getKeyspace());
        final MappingConfiguration mappingConfiguration = new MappingConfiguration.Builder()
                .withPropertyMapper(new DefaultPropertyMapper().setPropertyAccessStrategy(PropertyAccessStrategy.FIELDS))
                .build();
        mappingManager = new MappingManager(session, mappingConfiguration);
        initDone.set(true);
    }

    public void teardown() {
        try {
            if (initDone.get()) {
                if (session != null && !session.isClosed()) {
                    session.close();
                }
                if (cluster != null && !cluster.isClosed()) {
                    cluster.close();
                }
            }
        } finally {
            this.initDone.set(false);
        }

    }

    public Session getSession() {
        return this.session;
    }

    public MappingManager getMappingManager() {
        return this.mappingManager;
    }
}
