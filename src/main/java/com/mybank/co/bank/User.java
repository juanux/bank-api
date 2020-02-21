package com.mybank.co.bank;

import java.util.Date;
import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String lastName;
    private EDocumentType documentType;
    private String documentId;
    private String email;
    private Date birthDay;
    private String EGender;

    public User(UUID id, String name, String lastName, EDocumentType documentType, String documentId, String email, Date birthDay, String EGender) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.documentType = documentType;
        this.documentId = documentId;
        this.email = email;
        this.birthDay = birthDay;
        this.EGender = EGender;
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

    public Date getBirthDay() {
        return birthDay;
    }

    public String getEGender() {
        return EGender;
    }
}


enum EDocumentType{
    PASSPORT,
    DNI,
    SCN
}

enum EGender{
    MALE,
    FEMALE
}
