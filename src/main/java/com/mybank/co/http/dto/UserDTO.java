package com.mybank.co.http.dto;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Class that represent the transfer object for an user
 */
public class UserDTO implements DTO {

    private Optional<UUID> id;
    private String name;
    private String lastName;
    private String documentType;
    private String documentId;
    private String email;
    private Date birthDay;
    private String gender;
    private AccountDTO account;

    public UserDTO(){}

    public UserDTO(Optional<UUID> id, String name, String lastName, String documentType, String documentId, String email, Date birthDay, String gender, AccountDTO account) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.documentType = documentType;
        this.documentId = documentId;
        this.email = email;
        this.birthDay = birthDay;
        this.gender = gender;
        this.account = account;
    }

    public Optional<UUID> getId() {
        return id;
    }

    public void setId(Optional<UUID> id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(getId(), userDTO.getId()) &&
                Objects.equals(getName(), userDTO.getName()) &&
                Objects.equals(getLastName(), userDTO.getLastName()) &&
                Objects.equals(getDocumentType(), userDTO.getDocumentType()) &&
                Objects.equals(getDocumentId(), userDTO.getDocumentId()) &&
                Objects.equals(getEmail(), userDTO.getEmail()) &&
                Objects.equals(getBirthDay(), userDTO.getBirthDay()) &&
                Objects.equals(getGender(), userDTO.getGender()) &&
                Objects.equals(getAccount(), userDTO.getAccount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLastName(), getDocumentType(), getDocumentId(), getEmail(), getBirthDay(), getGender(), getAccount());
    }
}
