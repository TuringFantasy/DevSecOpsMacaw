package com.cfx.im.dto;


@com.datastax.driver.mapping.annotations.Table(name = "image")
public class Image {

    public Image() {}

    @com.datastax.driver.mapping.annotations.PartitionKey(value = 0)
    private java.util.UUID Id;
    public java.util.UUID getId() {
        return Id;
    }
    public void setId(java.util.UUID Id) {
        this.Id = Id;
    }

    private String Name;
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }

    private String Description;
    public String getDescription() {
        return Description;
    }
    public void setDescription(String Description) {
        this.Description = Description;
    }

    private java.nio.ByteBuffer Object;
    public java.nio.ByteBuffer getObject() {
        return Object;
    }
    public void setObject(java.nio.ByteBuffer Object) {
        this.Object = Object;
    }

    private String Url;
    public String getUrl() {
        return Url;
    }
    public void setUrl(String Url) {
        this.Url = Url;
    }

    private java.util.Date CreatedDate;
    public java.util.Date getCreatedDate() {
        return CreatedDate;
    }
    public void setCreatedDate(java.util.Date CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

    private String CustomerId;
    public String getCustomerId() {
        return CustomerId;
    }
    public void setCustomerId(String CustomerId) {
        this.CustomerId = CustomerId;
    }

}
