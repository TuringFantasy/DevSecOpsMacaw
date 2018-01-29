package com.cfx.im.db;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import com.cfx.service.api.config.Configuration;

public class DBConfig {

    private static final String COLON = ":";

    private static final Integer DEFAULT_CASSANDRA_PORT = 9042;

    private static final String DB_CASSANDRA_KEYSPACE = "db.cassandra.keyspace";
    private static final String DB_CASSANDRA_PASSWORD = "db.cassandra.password";
    private static final String DB_CASSANDRA_USERNAME = "db.cassandra.username";
    private static final String DB_CASSANDRA_CLUSTER_NODES = "db.cassandra.clusterNodes";

    private List<InetSocketAddress> addresses;

    private String username;
    private String password;
    private String keyspace;

    public DBConfig loadFromConfiguration(final Configuration c) throws IOException {
        final String nodeAndPorts = (String) c.requireValue(DB_CASSANDRA_CLUSTER_NODES).getValue();
        String[] nodeAndPortsArr = nodeAndPorts.split(",");

        this.addresses = new ArrayList<>();
        for (String nodeAndPort : nodeAndPortsArr) {
            String node = nodeAndPort;
            Integer port = DEFAULT_CASSANDRA_PORT;
            if (nodeAndPort.indexOf(COLON) > 0) {
                int index = nodeAndPort.indexOf(COLON);
                node = nodeAndPort.substring(0, index);
                port = Integer.parseInt(nodeAndPort.substring(index + 1));
            }
            InetSocketAddress address = new InetSocketAddress(node, port);
            this.addresses.add(address);
        }

        username = (String) c.requireValue(DB_CASSANDRA_USERNAME).getValue();
        password = (String) c.requireValue(DB_CASSANDRA_PASSWORD).getValue();
        keyspace = (String) c.requireValue(DB_CASSANDRA_KEYSPACE).getValue();

        return this;
    }

    public List<InetSocketAddress> getAddresses() {
        return addresses;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getKeyspace() {
        return this.keyspace;
    }
}
