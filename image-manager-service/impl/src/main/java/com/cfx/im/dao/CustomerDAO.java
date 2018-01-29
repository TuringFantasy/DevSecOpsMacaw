package com.cfx.im.dao;


public class CustomerDAO extends BaseDAO<com.cfx.im.dto.Customer> {

    private static com.datastax.driver.mapping.Mapper<com.cfx.im.dto.Customer> mapper;

    public CustomerDAO() { 
        if (mapper == null) {
            mapper = getMappingManager().mapper(com.cfx.im.dto.Customer.class);
        }
    }

    public String getCFName() {
        return "customer";
    }

    protected java.util.List<com.cfx.im.dto.Customer> getDtosFromResultSet(com.datastax.driver.core.ResultSet resultSet) {
        return mapper.map(resultSet).all();
    }

    public com.cfx.im.dto.Customer findByPk( String CustomerId) {
        if (CustomerId == null) {
            return null;
        }
        return mapper.get( CustomerId);
    }

    public void insert(com.cfx.im.dto.Customer dto) {
        if (dto == null) {
            throw new RuntimeException("Cannot insert null Customer in DB.");
        }
        if ( dto.getCustomerId() == null) {
            throw new RuntimeException("Cannot insert Customer in DB with PK column as null");
        } else {
            com.cfx.im.dto.Customer dtoById = null;
            try {
                dtoById = findByPk( dto.getCustomerId());
            } catch (Throwable e) {
                // ignore this exception as we are just checking if the object is present in DB or not
            }
            if (dtoById != null) {
                throw new RuntimeException("Cannot insert in DB as Customer already exists in DB with this PK.");
            }
        }
        if (dto.getName() == null) {
            throw new RuntimeException("Cannot insert Customer in DB with null value for Name ");
        }
        if (dto.getEmailId() == null) {
            throw new RuntimeException("Cannot insert Customer in DB with null value for EmailId ");
        }
        mapper.save(dto);
    }
}
