package io.axtrading.botfallback.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseTransfer {
    List<Failure> failures;
}
