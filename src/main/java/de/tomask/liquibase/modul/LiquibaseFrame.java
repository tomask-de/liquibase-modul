package de.tomask.liquibase.modul;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;

/**
 * @author tomask-de (develop@tomask.de)
 */
public final class LiquibaseFrame {

    private final Database database;

    public LiquibaseFrame(final Connection connection) throws DatabaseException {
        this.database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
    }

    public void updateDatabase(final String changelog) throws LiquibaseException {
        Liquibase liquibase = new Liquibase(changelog, new ClassLoaderResourceAccessor(), database);
        liquibase.update("");
    }
}