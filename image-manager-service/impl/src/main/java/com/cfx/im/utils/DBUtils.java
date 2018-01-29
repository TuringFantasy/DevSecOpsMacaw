package com.cfx.im.utils;

import java.util.Date;
import java.util.UUID;

import com.cfx.im.dao.CustomerDAO;
import com.cfx.im.dao.ImageApprovalDAO;
import com.cfx.im.dao.ImageDAO;
import com.cfx.im.dto.Customer;
import com.cfx.im.dto.Image;
import com.cfx.im.dto.ImageApproval;

import http.macaw.io.service.image.manager.rev180123.ApprovalStatus;
import http.macaw.io.service.image.manager.rev180123.ImageManagerServiceException;

public interface DBUtils {
    static Customer getCustomer(String id) throws ImageManagerServiceException {

        // Check customer exists
        Customer dto = new CustomerDAO().findByPk(id);
        if (dto == null) {
            throw new ImageManagerServiceException(format("Customer %s not exists", id));
        }

        return dto;
    }

    static String format(String format, Object... objects) {
        return String.format(format, objects);
    }

    static Image getImage(UUID id) throws ImageManagerServiceException {
        Image dto = new ImageDAO().findByPk(id);

        if (dto == null) {
            throw new ImageManagerServiceException(format("Image %s not exists", id));
        }

        return dto;
    }

    static void createPendingApproval(Image image) {
        ImageApproval dto = new ImageApproval();
        dto.setCustomerId(image.getCustomerId());
        dto.setImageId(image.getId());
        dto.setStatus(ApprovalStatus.PENDING.name());
        dto.setUpdateAt(new Date().getTime());

        ImageApprovalDAO dao = new ImageApprovalDAO();
        dao.insert(dto);
    }
}
