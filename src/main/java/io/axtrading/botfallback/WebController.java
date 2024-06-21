package io.axtrading.botfallback;

import io.axtrading.botfallback.data.dto.Failure;
import io.axtrading.botfallback.services.FailureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/api/1.0/failures")
public class WebController {

    private static final Logger log = LoggerFactory.getLogger(WebController.class);
    final private FailureService failureService;

    public WebController(FailureService failureService) {
        this.failureService = failureService;
    }

    @GetMapping(value = "/add") // -> POST !
    public String addFailure(@RequestParam(value = "some-text") String someText) {
        failureService.add(someText);
        return "ADDED. someText=" + someText;
    }

    /**
     * @param id     - id
     * @param active - state
     * @return list of failures
     * @throws InterruptedException description
     * @throws ExecutionException description
     */
    @GetMapping(value = "/get-async/{id}")
    @ResponseBody
    public ResponseEntity<List<Failure>> getFailuresAsync(@PathVariable Long id,
                                                          @RequestParam(value = "active", defaultValue = "true") boolean active
    ) throws InterruptedException, ExecutionException {
        log.info("START");
        CompletableFuture<List<Failure>> completableFuture2 = failureService.getInfo(id + 1, "name_%s".formatted(id + 1));
        log.info("STARTED{}", id + 1);
        CompletableFuture<List<Failure>> completableFuture = failureService.getInfo(id, "name_%s".formatted(id));
        log.info("STARTED{}", id);

        CompletableFuture.allOf(completableFuture, completableFuture2).join();


        List<Failure> failures = completableFuture.get();
        if (completableFuture2.isDone()) {
            failures.addAll(completableFuture2.get());
        }

        log.info("size=%s".formatted(failures.size()));
        return new ResponseEntity<>(failures, HttpStatus.OK);
        //return new ResponseTransfer(failures);
    }
}
