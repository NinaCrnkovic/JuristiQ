package hr.algebra.juristiq.controllers;

import hr.algebra.juristiq.components.ActionNotificationScheduler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestSchedulerController {


    private ActionNotificationScheduler scheduler;

    @GetMapping("/test-email")
    public String testEmailScheduler() {
        scheduler.sendTestReminder();
        return "E-mail podsjetnici su poslani!";
    }
}