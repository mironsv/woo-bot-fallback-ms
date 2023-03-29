package io.axtrading.botfallback.repositories;

import java.sql.SQLException;

public interface TableOperations {
    void createTable() throws SQLException;

    String updateTable() throws SQLException;

    boolean deleteRecordTable(int id) throws SQLException;

    boolean readTable() throws SQLException;
}
