package com.cfx.im.dao;


public class ImageApprovalDAO extends BaseDAO<com.cfx.im.dto.ImageApproval> {

    private static com.datastax.driver.mapping.Mapper<com.cfx.im.dto.ImageApproval> mapper;

    public ImageApprovalDAO() { 
        if (mapper == null) {
            mapper = getMappingManager().mapper(com.cfx.im.dto.ImageApproval.class);
        }
    }

    public String getCFName() {
        return "imageapproval";
    }

    protected java.util.List<com.cfx.im.dto.ImageApproval> getDtosFromResultSet(com.datastax.driver.core.ResultSet resultSet) {
        return mapper.map(resultSet).all();
    }

    public com.cfx.im.dto.ImageApproval findByPk( String CustomerId, java.util.UUID ImageId) {
        if (CustomerId == null) {
            return null;
        }
        if (ImageId == null) {
            return null;
        }
        return mapper.get( CustomerId, ImageId);
    }

    public void deleteByPk( String CustomerId, java.util.UUID ImageId) {
        com.cfx.im.dto.ImageApproval dto = findByPk( CustomerId, ImageId);
        if (dto == null) {
            throw new RuntimeException("ImageApproval not found with PK.");
        }
        mapper.delete( CustomerId, ImageId);
    }

    public void delete(com.cfx.im.dto.ImageApproval dto) {
        if (dto == null) {
            return;
        }
        deleteByPk( dto.getCustomerId(), dto.getImageId());
    }

    public void insert(com.cfx.im.dto.ImageApproval dto) {
        if (dto == null) {
            throw new RuntimeException("Cannot insert null ImageApproval in DB.");
        }
        if ( dto.getCustomerId() == null || dto.getImageId() == null) {
            throw new RuntimeException("Cannot insert ImageApproval in DB with PK column as null");
        } else {
            com.cfx.im.dto.ImageApproval dtoById = null;
            try {
                dtoById = findByPk( dto.getCustomerId(), dto.getImageId());
            } catch (Throwable e) {
                // ignore this exception as we are just checking if the object is present in DB or not
            }
            if (dtoById != null) {
                throw new RuntimeException("Cannot insert in DB as ImageApproval already exists in DB with this PK.");
            }
        }
        mapper.save(dto);
    }

    public void upsert(com.cfx.im.dto.ImageApproval dto) {
        if (dto == null) {
            throw new RuntimeException("Cannot upsert null ImageApproval in DB.");
        }
        if ( dto.getCustomerId() == null || dto.getImageId() == null) {
            throw new RuntimeException("Cannot upsert ImageApproval in DB with PK column as null");
        }
        mapper.save(dto);
    }

    public com.cfx.im.dto.ImageApproval update(com.cfx.im.dto.ImageApproval dto) {
        if (dto == null) {
            throw new RuntimeException("Cannot update null ImageApproval in DB.");
        }
        if ( dto.getCustomerId() == null || dto.getImageId() == null) {
            throw new RuntimeException("Cannot udpate ImageApproval in DB with PK column as null");
        } else {
            com.cfx.im.dto.ImageApproval dtoObj = findByPk( dto.getCustomerId(), dto.getImageId());
            if (dtoObj == null) {
                throw new RuntimeException("ImageApproval not found with PK.");
            }
        }
        mapper.save(dto);

        return dto;
    }

}
