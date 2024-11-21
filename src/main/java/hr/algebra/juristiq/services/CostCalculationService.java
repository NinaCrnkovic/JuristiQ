package hr.algebra.juristiq.services;

import hr.algebra.juristiq.enums.ActionType;
import hr.algebra.juristiq.enums.LitigationCaseType;
import hr.algebra.juristiq.enums.NonLitigationActionType;
import hr.algebra.juristiq.enums.NonLitigationCaseType;
import hr.algebra.juristiq.models.TariffRule;


import hr.algebra.juristiq.models.CostCalculation;

import lombok.AllArgsConstructor;


import java.util.List;



import hr.algebra.juristiq.models.CostItem;
import hr.algebra.juristiq.repositories.TariffRuleRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@AllArgsConstructor

public class CostCalculationService {

    private final TariffRuleRepository tariffRuleRepository;

    public double calculateLitigationCost(ActionType actionType, LitigationCaseType caseType, double vps) {
        TariffRule rule = tariffRuleRepository
                .findByActionTypeAndLitigationCaseType(actionType, caseType)
                .stream().findFirst().orElseThrow(() ->
                        new IllegalArgumentException("Pravilo nije pronađeno za sudski predmet."));
        return rule.getBaseFee() + (rule.getPercentage() / 100.0) * vps;
    }

    public double calculateNonLitigationCost(NonLitigationActionType actionType, NonLitigationCaseType caseType, double vps) {
        TariffRule rule = tariffRuleRepository
                .findByNonLitigationActionTypeAndNonLitigationCaseType(actionType, caseType)
                .stream().findFirst().orElseThrow(() ->
                        new IllegalArgumentException("Pravilo nije pronađeno za izvanparnični predmet."));
        return rule.getBaseFee() + (rule.getPercentage() / 100.0) * vps;
    }
}
