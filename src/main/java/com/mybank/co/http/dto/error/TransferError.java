package com.mybank.co.http.dto.error;

import com.mybank.co.http.dto.DTO;

import java.util.Objects;

public class TransferError implements DTO {

    private String code;
    private String message;

    public TransferError() {
    }

    public TransferError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransferError)) return false;
        TransferError that = (TransferError) o;
        return Objects.equals(getCode(), that.getCode()) &&
                Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getMessage());
    }
}
