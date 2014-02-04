/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das;

import com.github.obullxl.lang.enums.EnumBase;

/**
 * Dialects and dialect families.
 * 
 * @author obullxl@gmail.com
 * @version $Id: SQLDialect.java, V1.0.1 2014年2月3日 上午10:01:13 $
 */
public enum SQLDialect implements EnumBase {

    /**
     * The standard SQL dialect family.
     *
     * @deprecated - Do not reference this pseudo-dialect. It is only used for unit testing
     */
    @Deprecated
    SQL99(null, false),

    // -------------------------------------------------------------------------
    // SQL dialects for free usage
    // -------------------------------------------------------------------------

    /**
     * The CUBRID SQL dialect family.
     */
    CUBRID("CUBRID", false),

    /**
     * The Apache Derby SQL dialect family.
     */
    DERBY("Derby", false),

    /**
     * The Firebird SQL dialect family.
     */
    FIREBIRD("Firebird", false),

    /**
     * The H2 SQL dialect family.
     */
    H2("H2", false),

    /**
     * The Hypersonic SQL dialect family.
     */
    HSQLDB("HSQLDB", false),

    /**
     * The MariaDB dialect family.
     */
    MARIADB("MariaDB", false),

    /**
     * The MySQL dialect family.
     */
    MYSQL("MySQL", false),

    /**
     * The PostgreSQL dialect family.
     */
    POSTGRES("Postgres", false),

    /**
     * The SQLite dialect family.
     */
    SQLITE("SQLite", false),
    
    /**
     * The SQLite dialect family.
     */
    ORACLE("Oracle", true),
    //
    ;

    private final String     name;
    private final boolean    commercial;
    private final SQLDialect family;

    private SQLDialect(String name, boolean commercial) {
        this(name, commercial, null);
    }

    private SQLDialect(String name, boolean commercial, SQLDialect family) {
        this.name = name;
        this.commercial = commercial;
        this.family = family;
    }

    /**
     * Whether this dialect is supported with the jOOQ commercial license only.
     */
    public final boolean commercial() {
        return commercial;
    }

    /**
     * The dialect family.
     * <p>
     * This returns the dialect itself, if it has no "parent family". E.g.
     * <code><pre>
     * SQLSERVER == SQLSERVER2012.family();
     * SQLSERVER == SQLSERVER2008.family();
     * SQLSERVER == SQLSERVER.family();
     * </pre></code>
     */
    public final SQLDialect family() {
        return family == null ? this : family;
    }

    /**
     * The name of this dialect as it appears in related class names.
     */
    public final String getName() {
        return name;
    }

    /**
     * The name of this dialect as it appears in related package names.
     */
    public final String getNameLC() {
        return name == null ? null : name.toLowerCase();
    }

    /**
     * The name of this dialect as it appears in related enum values.
     */
    public final String getNameUC() {
        return name == null ? null : name.toUpperCase();
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#id()
     */
    public int id() {
        return this.ordinal();
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#code()
     */
    public String code() {
        return this.name;
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#desp()
     */
    public String desp() {
        return this.name;
    }
    
}
