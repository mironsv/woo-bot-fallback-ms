package io.axtrading.botfallback.repositories;

import io.axtrading.botfallback.dto.Failure;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class NewFailureRepository extends BaseRepository implements TableOperations {

    public NewFailureRepository() throws SQLException {
        super("new_failure");
        createTable();
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS " + tableName + "(" +
                "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "remaining_attempts SMALLINT NOT NULL," +
                "failureType VARCHAR(20))", "Table " + tableName + " is created!");
    }

    @Override
    public String updateTable() throws SQLException {
        return "Give arguments: String name, short age, String content!";
    }

    @Override
    public boolean deleteRecordTable(int id) throws SQLException {
        return super.executeSqlStatement("DELETE FROM " + this.tableName +
                " WHERE id=" + id + ";");
    }

    @Override
    public boolean readTable() throws SQLException {
        return false;
    }

    public String readTable(int id, String name) throws SQLException {
        return "False";
    }

    public Long save(Failure failure) {
        try {
            Integer clientID = failure.getClientID();
            String failureType = failure.getFailureType();
            if (super.executeSqlStatement("INSERT INTO " + this.tableName +
                            " (client_id, failure_type)" +
                            " VALUES ('" + clientID + "', '" + failureType + "');",
                    clientID + " " + failureType + " was inserted into " + this.tableName)) {
                ResultSet resultSet = super.readSQLTable("SELECT id FROM " + this.tableName +
                        " WHERE client_id = '" + clientID + "' AND failure_type = '" + failureType + "';");
                if (resultSet.next()) {
                    return resultSet.getLong("id");
                } else return -1L;
            } else return -1L;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
