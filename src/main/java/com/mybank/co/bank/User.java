package com.mybank.co.bank;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * This class represent all the information of a bank user
 */
public class User {
    private String id;
    private String name;
    private String lastName;
    private EDocumentType documentType;
    private String documentId;
    private String email;
    private LocalDate birthDay;
    private EGender gender;

    /**
     * Default user constructor
     * @param id
     * @param name
     * @param lastName
     * @param documentType
     * @param documentId
     * @param email
     * @param birthDay
     * @param gender
     */
    public User(String id, String name, String lastName, EDocumentType documentType, String documentId, String email, LocalDate birthDay, EGender gender) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.documentType = documentType;
        this.documentId = documentId;
        this.email = email;
        this.birthDay = birthDay;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public EDocumentType getDocumentType() {
        return documentType;
    }

    public String getDocumentId() {
        return documentId;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public EGender getGender() {
        return gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getLastName(), user.getLastName()) &&
                getDocumentType() == user.getDocumentType() &&
                Objects.equals(getDocumentId(), user.getDocumentId()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getBirthDay(), user.getBirthDay()) &&
                getGender() == user.getGender();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLastName(), getDocumentType(), getDocumentId(), getEmail(), getBirthDay(), getGender());
    }
}

