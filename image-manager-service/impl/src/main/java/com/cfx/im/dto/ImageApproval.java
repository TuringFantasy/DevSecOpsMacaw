package com.cfx.im.dto;


@com.datastax.driver.mapping.annotations.Table(name = "imageapproval")
public class ImageApproval {

    public ImageApproval() {}

    @com.datastax.driver.mapping.annotations.ClusteringColumn(value = 0)
    private String CustomerId;
    public String getCustomerId() {
        return CustomerId;
    }
    public void setCustomerId(String CustomerId) {
        this.CustomerId = CustomerId;
    }

    @com.datastax.driver.mapping.annotations.ClusteringColumn(value = 1)
    private java.util.UUID ImageId;
    public java.util.UUID getImageId() {
        return ImageId;
    }
    public void setImageId(java.util.UUID ImageId) {
        this.ImageId = ImageId;
    }

    private String Status;
    public String getStatus() {
        return Status;
    }
    public void setStatus(String Status) {
        this.Status = Status;
    }

    private String Reason;
    public String getReason() {
        return Reason;
    }
    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    private Long UpdateAt;
    public Long getUpdateAt() {
        return UpdateAt;
    }
    public void setUpdateAt(Long UpdateAt) {
        this.UpdateAt = UpdateAt;
    }

}
