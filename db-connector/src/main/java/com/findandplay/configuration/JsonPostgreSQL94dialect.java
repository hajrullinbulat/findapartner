package com.findandplay.configuration;

import org.hibernate.dialect.PostgreSQL94Dialect;

import java.sql.Types;

/**
 * Created by hajrullinbulat on 30.04.17.
 */
public class JsonPostgreSQL94dialect extends PostgreSQL94Dialect {
    public JsonPostgreSQL94dialect() {
        super();
        this.registerColumnType(Types.JAVA_OBJECT, "json");
    }
}
