package io.axtrading.botfallback.data.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@Table(name = "failures")
@NoArgsConstructor
public class Failure {

    public Failure(Date someDate, String someText) {
        this.someDate = someDate;
        this.someText = someText;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date someDate;
    private String someText;

}
