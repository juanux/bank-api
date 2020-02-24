package com.mybank.co.http.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class that represent the transfer object for an transfer
 */
public class TransferDTO implements DTO {

    private String originAccountId;
    private String targetAccountId;
    private Double amount;
    private String status;
    private LocalDateTime dateTime;

    public TransferDTO(){}

    public TransferDTO(String originAccountId, String targetAccountId, Double amount, String status, LocalDateTime dateTime) {
        this.originAccountId = originAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
        this.status = status;
        this.dateTime = dateTime;
    }

    public String getOriginAccountId() {
        return originAccountId;
    }

    public void setOriginAccountId(String originAccountId) {
        this.originAccountId = originAccountId;
    }

    public String getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(String targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransferDTO)) return false;
        TransferDTO that = (TransferDTO) o;
        return Objects.equals(getOriginAccountId(), that.getOriginAccountId()) &&
                Objects.equals(getTargetAccountId(), that.getTargetAccountId()) &&
                Objects.equals(getAmount(), that.getAmount()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getDateTime(), that.getDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOriginAccountId(), getTargetAccountId(), getAmount(), getStatus(), getDateTime());
    }
}
