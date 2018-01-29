package com.cfx.im.utils;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cfx.im.dao.MinioDAO;

import http.macaw.io.service.image.manager.rev180123.ImageManagerServiceException;

public interface ImageReader {
    static final Logger LOGGER = LoggerFactory.getLogger(ImageReader.class);

    static ByteBuffer readImageFromMinio(String path) throws ImageManagerServiceException {
        String url = path.toLowerCase();
        if (url == null || !url.startsWith("minio")) {
            throw new ImageManagerServiceException("Url not supported. Unable to read image");
        }

        String[] urlParts = url.split("/");

        String bucket = urlParts[urlParts.length - 2];
        String fileName = urlParts[urlParts.length - 1];

        final ByteBuffer bb;
        try {
            bb = new MinioDAO().getImageAsByteBuffer(bucket, fileName);
        } catch (Exception e) {
            LOGGER.error("Unable to read image from minio", e);
            throw new ImageManagerServiceException("Unable to read image from minio");
        }

        return bb;
    }
}
