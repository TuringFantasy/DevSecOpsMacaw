package com.cfx.im.dto;


@com.datastax.driver.mapping.annotations.Table(name = "customer")
public class Customer {

    public Customer() {}

    @com.datastax.driver.mapping.annotations.PartitionKey(value = 0)
    private String CustomerId;
    public String getCustomerId() {
        return CustomerId;
    }
    public void setCustomerId(String CustomerId) {
        this.CustomerId = CustomerId;
    }

    private String Name;
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }

    private String Address;
    public String getAddress() {
        return Address;
    }
    public void setAddress(String Address) {
        this.Address = Address;
    }

    private String Description;
    public String getDescription() {
        return Description;
    }
    public void setDescription(String Description) {
        this.Description = Description;
    }

    private Long DOB;
    public Long getDOB() {
        return DOB;
    }
    public void setDOB(Long DOB) {
        this.DOB = DOB;
    }

    private String EmailId;
    public String getEmailId() {
        return EmailId;
    }
    public void setEmailId(String EmailId) {
        this.EmailId = EmailId;
    }

}
