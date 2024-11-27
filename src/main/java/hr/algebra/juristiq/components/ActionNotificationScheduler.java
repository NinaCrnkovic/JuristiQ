package hr.algebra.juristiq.components;

import hr.algebra.juristiq.enums.ActionType;
import hr.algebra.juristiq.models.Action;
import hr.algebra.juristiq.models.LitigationCase;
import hr.algebra.juristiq.services.ActionService;
import hr.algebra.juristiq.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor

public class ActionNotificationScheduler {


    private EmailService emailService; // Servis za slanje e-mailova


    private ActionService actionService; // Servis za dohvat akcija

    // Scheduled task: svaki dan u 8:00
    @Scheduled(cron = "0 0 8 * * ?")
    public void sendHearingReminders() {
        LocalDate tomorrow = LocalDate.now().plusDays(1); // Podsjetnici za sutrašnje ročišta

        // Dohvati sve akcije
        List<Action> allActions = actionService.getAllActions();

        // Filtriraj samo akcije ročišta s odgovarajućim datumom
        List<Action> hearings = allActions.stream()
                .filter(action -> (action.getType() == ActionType.PRISUSTVOVANJE_ROČIŠTU || action.getType() == ActionType.ZASTUPANJE_PRED_SUDOM)
                        && action.getDate().equals(tomorrow))
                .collect(Collectors.toList());

        // Slanje podsjetnika za svaku akciju ročišta
        for (Action hearing : hearings) {
            LitigationCase litigationCase = hearing.getLitigationCase();
            if (litigationCase != null) {
                // Dohvaćanje suda iz LitigationCase
                String court = litigationCase.getCourt() != null ? litigationCase.getCourt().name() : "N/A";

                // Iteracija kroz zastupane stranke za slanje e-mailova njihovim odvjetnicima
                litigationCase.getRepresentedParties().forEach(party -> {
                    if (party.getLawyer() != null && party.getLawyer().getEmail() != null) {
                        String subject = "Podsjetnik: Ročište za predmet " + litigationCase.getDesignation();
                        String text = "Poštovani,\n\n"
                                + "Podsjećamo vas na ročište vezano uz predmet \"" + litigationCase.getDesignation()
                                + "\", koje je zakazano za " + hearing.getDate() + " u " + (hearing.getTime() != null ? hearing.getTime() : "N/A") + ".\n\n"
                                + "Sud: " + court + "\n\n"
                                + "Srdačan pozdrav,\nVaš JuristiQ tim.";

                        // Slanje e-maila odvjetniku
                        emailService.sendEmail(
                                party.getLawyer().getEmail(),
                                subject,
                                text
                        );
                    }
                });
            }
        }
    }



    public void sendTestReminder() {
        LocalDate tomorrow = LocalDate.now().plusDays(1); // Podsjetnici za sutrašnji datum

        // Dohvati sve akcije
        List<Action> allActions = actionService.getAllActions();

        // Filtriraj samo ročišta
        List<Action> hearings = allActions.stream()
                .filter(action -> (action.getType() == ActionType.PRISUSTVOVANJE_ROČIŠTU || action.getType() == ActionType.ZASTUPANJE_PRED_SUDOM)
                        && action.getDate().equals(tomorrow))
                .collect(Collectors.toList());

        // Slanje podsjetnika
        for (Action hearing : hearings) {
            LitigationCase litigationCase = hearing.getLitigationCase();
            if (litigationCase != null) {
                String court = litigationCase.getCourt() != null ? litigationCase.getCourt().getDisplayName() : "N/A";

                litigationCase.getRepresentedParties().forEach(party -> {
                    if (party.getLawyer() != null && party.getLawyer().getEmail() != null) {
                        String subject = "Podsjetnik: Ročište za predmet " + litigationCase.getDesignation();
                        String text = "Poštovani,\n\n"
                                + "Podsjećamo vas na ročište vezano uz predmet \"" + litigationCase.getDesignation()
                                + "\", koje je zakazano za " + hearing.getDate() + " u " + (hearing.getTime() != null ? hearing.getTime() : "N/A") + ".\n\n"
                                + "Sud: " + court + "\n\n"
                                + "Srdačan pozdrav,\nVaš JuristiQ tim.";

                        emailService.sendEmail(
                                party.getLawyer().getEmail(),
                                subject,
                                text
                        );
                    }
                });
            }
        }
    }

}