package hr.algebra.juristiq.config;

import hr.algebra.juristiq.enums.ActionType;
import hr.algebra.juristiq.enums.LitigationCaseType;
import hr.algebra.juristiq.enums.NonLitigationActionType;
import hr.algebra.juristiq.enums.NonLitigationCaseType;
import hr.algebra.juristiq.models.TariffRule;
import hr.algebra.juristiq.repositories.TariffRuleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor

public class DataInitializer implements CommandLineRunner {

    private final TariffRuleRepository tariffRuleRepository;



    @Override
    public void run(String... args) throws Exception {
        if (tariffRuleRepository.count() == 0) {
            // Dodavanje pravila za sve parnične radnje i vrste predmeta
            for (ActionType actionType : ActionType.values()) {
                for (LitigationCaseType caseType : LitigationCaseType.values()) {
                    TariffRule rule = new TariffRule();
                    rule.setActionType(actionType);
                    rule.setLitigationCaseType(caseType);
                    rule.setBaseFee(100); // Fiksni iznos (EUR)
                    rule.setPercentage(2.0); // Postotak VPS-a
                    tariffRuleRepository.save(rule);
                }
            }

            // Dodavanje pravila za sve izvanparnične radnje i vrste predmeta
            for (NonLitigationActionType actionType : NonLitigationActionType.values()) {
                for (NonLitigationCaseType caseType : NonLitigationCaseType.values()) {
                    TariffRule rule = new TariffRule();
                    rule.setNonLitigationActionType(actionType);
                    rule.setNonLitigationCaseType(caseType);
                    rule.setBaseFee(50); // Fiksni iznos (EUR)
                    rule.setPercentage(0.0); // Bez postotka
                    tariffRuleRepository.save(rule);
                }
            }
        }
    }

    private TariffRule createLitigationRule(ActionType actionType) {
        TariffRule rule = new TariffRule();
        rule.setActionType(actionType);

        switch (actionType) {
            case SASTAVLJANJE_TUŽBE:
                rule.setBaseFee(150.0);
                rule.setPercentage(3.0);
                break;

            case SASTAVLJANJE_ODGOVORA_NA_TUŽBU:
                rule.setBaseFee(120.0);
                rule.setPercentage(2.5);
                break;

            case SASTAVLJANJE_ŽALBE:
                rule.setBaseFee(130.0);
                rule.setPercentage(2.8);
                break;

            case SASTAVLJANJE_PRIGOVORA:
                rule.setBaseFee(110.0);
                rule.setPercentage(2.0);
                break;

            case SASTAVLJANJE_PODNESKA:
                rule.setBaseFee(100.0);
                rule.setPercentage(1.5);
                break;

            case PRISUSTVOVANJE_ROČIŠTU:
                rule.setBaseFee(100.0);
                rule.setPercentage(2.0);
                break;

            case SASTAVLJANJE_OVRŠNOG_PRIJEDLOGA:
                rule.setBaseFee(140.0);
                rule.setPercentage(2.5);
                break;

            case ZASTUPANJE_PRED_SUDOM:
                rule.setBaseFee(200.0);
                rule.setPercentage(5.0);
                break;

            case IZRADBA_STRATEGIJE:
                rule.setBaseFee(180.0);
                rule.setPercentage(4.0);
                break;

            case PREGOVARANJE:
                rule.setBaseFee(150.0);
                rule.setPercentage(3.5);
                break;

            case PREDSTAVLJANJE_U_MEDIJACIJI:
                rule.setBaseFee(180.0);
                rule.setPercentage(3.0);
                break;

            default:
                return null; // Ako nema specifičnog pravila
        }
        return rule;
    }


    private TariffRule createNonLitigationRule(NonLitigationActionType actionType) {
        TariffRule rule = new TariffRule();
        rule.setNonLitigationActionType(actionType);

        switch (actionType) {
            case SASTAVLJANJE_OPORUKE:
                rule.setBaseFee(80.0);
                rule.setPercentage(0.0);
                break;

            case SASTAVLJANJE_UGOVORA:
                rule.setBaseFee(120.0);
                rule.setPercentage(0.0);
                break;

            case UREĐENJE_VLASNIŠTVA:
                rule.setBaseFee(200.0);
                rule.setPercentage(5.0);
                break;

            case KONZULTACIJE:
                rule.setBaseFee(50.0);
                rule.setPercentage(0.0);
                break;

            case MEDIJACIJA:
                rule.setBaseFee(100.0);
                rule.setPercentage(1.0);
                break;

            case PISANJE_OPOMENE:
                rule.setBaseFee(50.0);
                rule.setPercentage(0.0);
                break;

            case PODNOŠENJE_PRITUŽBE:
                rule.setBaseFee(70.0);
                rule.setPercentage(1.0);
                break;

            case SAVJETOVANJE:
                rule.setBaseFee(60.0);
                rule.setPercentage(0.0);
                break;

            case IZRADBA_DOKUMENTACIJE:
                rule.setBaseFee(100.0);
                rule.setPercentage(0.0);
                break;

            case OVJERA_DOKUMENATA:
                rule.setBaseFee(50.0);
                rule.setPercentage(0.0);
                break;

            case REVIZIJA_UGOVORA:
                rule.setBaseFee(90.0);
                rule.setPercentage(0.0);
                break;

            case PRIPREMA_DOKUMENATA:
                rule.setBaseFee(70.0);
                rule.setPercentage(0.0);
                break;

            case DRUGO:
                rule.setBaseFee(60.0);
                rule.setPercentage(0.0);
                break;

            default:
                return null; // Ako nema specifičnog pravila
        }
        return rule;
    }

}