package io.axtrading.botfallback;

import io.axtrading.botfallback.services.FailureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/1.0/failures")
public class WebController {

    @Autowired
    private FailureService failureService;

    /**
     * fetches a list of failures
     *
     * @param active type of failures
     * @return a list of failures
     */
    @GetMapping(value = "/get")
    public String getFailures(@RequestParam(value = "active", defaultValue = "true") boolean active) {
        // return failureService.getFailures(active);
        return "You requested failures, active = " + active;
    }
}
