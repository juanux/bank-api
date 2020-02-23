/*
 * This file is generated by jOOQ.
 */
package com.mybank.co.dao.jooq.tables.records;


import com.mybank.co.dao.jooq.tables.User;

import java.time.LocalDate;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserRecord extends UpdatableRecordImpl<UserRecord> implements Record8<String, String, String, String, String, String, LocalDate, String> {

    private static final long serialVersionUID = 15673975;

    /**
     * Setter for <code>USER.id</code>.
     */
    public void setId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>USER.id</code>.
     */
    public String getId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>USER.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>USER.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>USER.last_name</code>.
     */
    public void setLastName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>USER.last_name</code>.
     */
    public String getLastName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>USER.document_type</code>.
     */
    public void setDocumentType(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>USER.document_type</code>.
     */
    public String getDocumentType() {
        return (String) get(3);
    }

    /**
     * Setter for <code>USER.document_id</code>.
     */
    public void setDocumentId(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>USER.document_id</code>.
     */
    public String getDocumentId() {
        return (String) get(4);
    }

    /**
     * Setter for <code>USER.email</code>.
     */
    public void setEmail(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>USER.email</code>.
     */
    public String getEmail() {
        return (String) get(5);
    }

    /**
     * Setter for <code>USER.birth_day</code>.
     */
    public void setBirthDay(LocalDate value) {
        set(6, value);
    }

    /**
     * Getter for <code>USER.birth_day</code>.
     */
    public LocalDate getBirthDay() {
        return (LocalDate) get(6);
    }

    /**
     * Setter for <code>USER.gender</code>.
     */
    public void setGender(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>USER.gender</code>.
     */
    public String getGender() {
        return (String) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<String, String, String, String, String, String, LocalDate, String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<String, String, String, String, String, String, LocalDate, String> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return User.USER.ID;
    }

    @Override
    public Field<String> field2() {
        return User.USER.NAME;
    }

    @Override
    public Field<String> field3() {
        return User.USER.LAST_NAME;
    }

    @Override
    public Field<String> field4() {
        return User.USER.DOCUMENT_TYPE;
    }

    @Override
    public Field<String> field5() {
        return User.USER.DOCUMENT_ID;
    }

    @Override
    public Field<String> field6() {
        return User.USER.EMAIL;
    }

    @Override
    public Field<LocalDate> field7() {
        return User.USER.BIRTH_DAY;
    }

    @Override
    public Field<String> field8() {
        return User.USER.GENDER;
    }

    @Override
    public String component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getLastName();
    }

    @Override
    public String component4() {
        return getDocumentType();
    }

    @Override
    public String component5() {
        return getDocumentId();
    }

    @Override
    public String component6() {
        return getEmail();
    }

    @Override
    public LocalDate component7() {
        return getBirthDay();
    }

    @Override
    public String component8() {
        return getGender();
    }

    @Override
    public String value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getLastName();
    }

    @Override
    public String value4() {
        return getDocumentType();
    }

    @Override
    public String value5() {
        return getDocumentId();
    }

    @Override
    public String value6() {
        return getEmail();
    }

    @Override
    public LocalDate value7() {
        return getBirthDay();
    }

    @Override
    public String value8() {
        return getGender();
    }

    @Override
    public UserRecord value1(String value) {
        setId(value);
        return this;
    }

    @Override
    public UserRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public UserRecord value3(String value) {
        setLastName(value);
        return this;
    }

    @Override
    public UserRecord value4(String value) {
        setDocumentType(value);
        return this;
    }

    @Override
    public UserRecord value5(String value) {
        setDocumentId(value);
        return this;
    }

    @Override
    public UserRecord value6(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public UserRecord value7(LocalDate value) {
        setBirthDay(value);
        return this;
    }

    @Override
    public UserRecord value8(String value) {
        setGender(value);
        return this;
    }

    @Override
    public UserRecord values(String value1, String value2, String value3, String value4, String value5, String value6, LocalDate value7, String value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserRecord
     */
    public UserRecord() {
        super(User.USER);
    }

    /**
     * Create a detached, initialised UserRecord
     */
    public UserRecord(String id, String name, String lastName, String documentType, String documentId, String email, LocalDate birthDay, String gender) {
        super(User.USER);

        set(0, id);
        set(1, name);
        set(2, lastName);
        set(3, documentType);
        set(4, documentId);
        set(5, email);
        set(6, birthDay);
        set(7, gender);
    }
}
