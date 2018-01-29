package com.cfx.im.dao;

import java.io.InputStream;
import java.nio.ByteBuffer;

import com.cfx.im.utils.ServiceConfiguration;
import com.google.common.io.ByteStreams;

import io.minio.MinioClient;

public class MinioDAO {

    public static final String PROPERTYNAME_OBJECTSTORE_HOST = "io.macaw.objectstorage.host";
    public static final String PROPERTYNAME_OBJECTSTORE_PORT = "io.macaw.objectstorage.port";
    public static final String PROPERTYNAME_OBJECTSTORE_KEY_ACCESS = "io.macaw.objectstorage.key.access";
    public static final String PROPERTYNAME_OBJECTSTORE_KEY_SECRET = "io.macaw.objectstorage.key.secret";

    MinioClient client;

    public MinioDAO() {
        String objectStoreEndpoint = String.format("https://%s:%s",
                ServiceConfiguration.getInstance().getRequiredProperty(PROPERTYNAME_OBJECTSTORE_HOST, "Invalid host for the object store"),
                ServiceConfiguration.getInstance().getRequiredProperty(PROPERTYNAME_OBJECTSTORE_PORT, "Invalid port for the object store"));
        try {
            this.client = new MinioClient(objectStoreEndpoint,
                    ServiceConfiguration.getInstance().getRequiredProperty(PROPERTYNAME_OBJECTSTORE_KEY_ACCESS,
                            "Invalid access key for the object store"),
                    ServiceConfiguration.getInstance().getRequiredProperty(PROPERTYNAME_OBJECTSTORE_KEY_SECRET,
                            "Invalid access secret for the object store"));
            this.client.ignoreCertCheck();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    public ByteBuffer getImageAsByteBuffer(String bucket, String filename) throws Exception {
        try (InputStream inputStream = getStream(bucket, filename)) {
            byte[] bytes = ByteStreams.toByteArray(inputStream);
            return ByteBuffer.wrap(bytes);
        }
    }

    private InputStream getStream(String bucket, String id) throws Exception {
        this.client.statObject(bucket, id);
        return this.client.getObject(bucket, id);
    }
}
