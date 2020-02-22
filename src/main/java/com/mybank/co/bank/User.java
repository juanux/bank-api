package com.mybank.co.bank;

import java.time.LocalDate;
import java.util.UUID;

/**
 * This class represent all the information of a bank user
 */
public class User {
    private UUID id;
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
    public User(UUID id, String name, String lastName, EDocumentType documentType, String documentId, String email, LocalDate birthDay, EGender gender) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.documentType = documentType;
        this.documentId = documentId;
        this.email = email;
        this.birthDay = birthDay;
        this.gender = gender;
    }

    public UUID getId() {
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
}

