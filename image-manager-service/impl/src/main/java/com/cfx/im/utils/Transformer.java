package com.cfx.im.utils;

import com.cfx.im.dto.Customer;
import com.cfx.im.dto.Image;
import http.macaw.io.service.image.manager.rev180123.DomainEntityInstantiator;

public interface Transformer {
    static http.macaw.io.service.image.manager.rev180123.Customer dtoToEntity(Customer dto) {
        http.macaw.io.service.image.manager.rev180123.Customer customer = DomainEntityInstantiator.getInstance()
                .newInstance(http.macaw.io.service.image.manager.rev180123.Customer.class);
        customer.setAddress(dto.getAddress());
        customer.setDescription(dto.getDescription());
        customer.setId(dto.getCustomerId());
        customer.setDob(dto.getDOB());
        customer.setEmailId(dto.getEmailId());

        return customer;
    }

    static http.macaw.io.service.image.manager.rev180123.Image dtoToEntity(Image dto) {
        http.macaw.io.service.image.manager.rev180123.Image image = DomainEntityInstantiator.getInstance()
                .newInstance(http.macaw.io.service.image.manager.rev180123.Image.class);
        image.setId(dto.getId().toString());
        image.setCreatedDate(dto.getCreatedDate().getTime());
        image.setCustomerId(dto.getCustomerId());
        image.setDescription(dto.getDescription());
        image.setName(dto.getName());
        image.setUrl(dto.getUrl());

        return image;
    }
}
