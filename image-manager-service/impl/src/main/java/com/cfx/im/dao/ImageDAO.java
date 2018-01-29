package com.cfx.im.dao;


public class ImageDAO extends BaseDAO<com.cfx.im.dto.Image> {

    private static com.datastax.driver.mapping.Mapper<com.cfx.im.dto.Image> mapper;

    public ImageDAO() { 
        if (mapper == null) {
            mapper = getMappingManager().mapper(com.cfx.im.dto.Image.class);
        }
    }

    public String getCFName() {
        return "image";
    }

    protected java.util.List<com.cfx.im.dto.Image> getDtosFromResultSet(com.datastax.driver.core.ResultSet resultSet) {
        return mapper.map(resultSet).all();
    }

    public com.cfx.im.dto.Image findByPk( java.util.UUID Id) {
        if (Id == null) {
            return null;
        }
        return mapper.get( Id);
    }

    public void deleteByPk( java.util.UUID Id) {
        com.cfx.im.dto.Image dto = findByPk( Id);
        if (dto == null) {
            throw new RuntimeException("Image not found with PK.");
        }
        mapper.delete( Id);
    }

    public void delete(com.cfx.im.dto.Image dto) {
        if (dto == null) {
            return;
        }
        deleteByPk( dto.getId());
    }

    public void insert(com.cfx.im.dto.Image dto) {
        if (dto == null) {
            throw new RuntimeException("Cannot insert null Image in DB.");
        }
        if ( dto.getId() == null) {
            throw new RuntimeException("Cannot insert Image in DB with PK column as null");
        } else {
            com.cfx.im.dto.Image dtoById = null;
            try {
                dtoById = findByPk( dto.getId());
            } catch (Throwable e) {
                // ignore this exception as we are just checking if the object is present in DB or not
            }
            if (dtoById != null) {
                throw new RuntimeException("Cannot insert in DB as Image already exists in DB with this PK.");
            }
        }
        mapper.save(dto);
    }

    public void upsert(com.cfx.im.dto.Image dto) {
        if (dto == null) {
            throw new RuntimeException("Cannot upsert null Image in DB.");
        }
        if ( dto.getId() == null) {
            throw new RuntimeException("Cannot upsert Image in DB with PK column as null");
        }
        mapper.save(dto);
    }

    public com.cfx.im.dto.Image update(com.cfx.im.dto.Image dto) {
        if (dto == null) {
            throw new RuntimeException("Cannot update null Image in DB.");
        }
        if ( dto.getId() == null) {
            throw new RuntimeException("Cannot udpate Image in DB with PK column as null");
        } else {
            com.cfx.im.dto.Image dtoObj = findByPk( dto.getId());
            if (dtoObj == null) {
                throw new RuntimeException("Image not found with PK.");
            }
        }
        mapper.save(dto);

        return dto;
    }

}
