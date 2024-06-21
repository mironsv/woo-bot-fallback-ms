package io.axtrading.botfallback.services;

import io.axtrading.botfallback.data.dto.Failure;
import io.axtrading.botfallback.data.repositories.NewFailureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class FailureService {


    final private NewFailureRepository newFailureRepository;

    public FailureService(NewFailureRepository newFailureRepository) {
        this.newFailureRepository = newFailureRepository;
    }

    public void add(String someText) {
        newFailureRepository.save(new Failure(Date.valueOf(LocalDate.now()), someText));
    }

    @Async
    public CompletableFuture<List<Failure>> getInfo(Long id, String name) {
        log.info("id=%s, name=%s".formatted(id, name));
        //Thread.sleep(id * 2000L);
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        List<Failure> failures = newFailureRepository.findAllById(ids);
        log.info("n failures=%s".formatted(failures.size()));
        failures.forEach(failure -> log.info("id=%s, some_text=%s".formatted(failure.getId(), failure.getSomeText())));
        return CompletableFuture.completedFuture(failures);
    }
}
