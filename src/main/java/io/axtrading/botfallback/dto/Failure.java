package io.axtrading.botfallback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class Failure {

    private long id; // INT NOT NULL AUTO_INCREMENT (PRIMARY KEY)
    private Timestamp timestamp;
    private Integer remaining_attempts; // SMALLINT NOT NULL
    private Integer sleep_timeout; // SMALLINT NOT NULL
    private Integer clientID; // SMALLINT NOT NULL
    private String failureType; // VARCHAR(20)
    private String failureParams; // VARCHAR(20)
    private String recoveryStrategy; // VARCHAR(20)

}
