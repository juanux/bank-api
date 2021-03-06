/*
 * This file is generated by jOOQ.
 */
package com.mybank.co.dao.jooq.tables;


import com.mybank.co.dao.jooq.DefaultSchema;
import com.mybank.co.dao.jooq.Keys;
import com.mybank.co.dao.jooq.tables.records.UserRecord;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row8;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class User extends TableImpl<UserRecord> {

    private static final long serialVersionUID = -923494892;

    /**
     * The reference instance of <code>USER</code>
     */
    public static final User USER = new User();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserRecord> getRecordType() {
        return UserRecord.class;
    }

    /**
     * The column <code>USER.id</code>.
     */
    public final TableField<UserRecord, String> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>USER.name</code>.
     */
    public final TableField<UserRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>USER.last_name</code>.
     */
    public final TableField<UserRecord, String> LAST_NAME = createField(DSL.name("last_name"), org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>USER.document_type</code>.
     */
    public final TableField<UserRecord, String> DOCUMENT_TYPE = createField(DSL.name("document_type"), org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>USER.document_id</code>.
     */
    public final TableField<UserRecord, String> DOCUMENT_ID = createField(DSL.name("document_id"), org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>USER.email</code>.
     */
    public final TableField<UserRecord, String> EMAIL = createField(DSL.name("email"), org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>USER.birth_day</code>.
     */
    public final TableField<UserRecord, LocalDate> BIRTH_DAY = createField(DSL.name("birth_day"), org.jooq.impl.SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>USER.gender</code>.
     */
    public final TableField<UserRecord, String> GENDER = createField(DSL.name("gender"), org.jooq.impl.SQLDataType.CHAR, this, "");

    /**
     * Create a <code>USER</code> table reference
     */
    public User() {
        this(DSL.name("USER"), null);
    }

    /**
     * Create an aliased <code>USER</code> table reference
     */
    public User(String alias) {
        this(DSL.name(alias), USER);
    }

    /**
     * Create an aliased <code>USER</code> table reference
     */
    public User(Name alias) {
        this(alias, USER);
    }

    private User(Name alias, Table<UserRecord> aliased) {
        this(alias, aliased, null);
    }

    private User(Name alias, Table<UserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> User(Table<O> child, ForeignKey<O, UserRecord> key) {
        super(child, key, USER);
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<UserRecord> getPrimaryKey() {
        return Keys.PK_USER;
    }

    @Override
    public List<UniqueKey<UserRecord>> getKeys() {
        return Arrays.<UniqueKey<UserRecord>>asList(Keys.PK_USER);
    }

    @Override
    public User as(String alias) {
        return new User(DSL.name(alias), this);
    }

    @Override
    public User as(Name alias) {
        return new User(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(String name) {
        return new User(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(Name name) {
        return new User(name, null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<String, String, String, String, String, String, LocalDate, String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }
}
