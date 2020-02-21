package com.mybank.co.http.dto;

import java.util.Objects;

public class ConfirmationDTO implements DTO {

    private String code;
    private String message;

    public ConfirmationDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ConfirmationDTO() {
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
        if (!(o instanceof ConfirmationDTO)) return false;
        ConfirmationDTO that = (ConfirmationDTO) o;
        return Objects.equals(getCode(), that.getCode()) &&
                Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getMessage());
    }
}
