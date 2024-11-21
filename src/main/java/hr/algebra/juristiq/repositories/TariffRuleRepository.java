package hr.algebra.juristiq.repositories;

import hr.algebra.juristiq.enums.ActionType;
import hr.algebra.juristiq.enums.LitigationCaseType;
import hr.algebra.juristiq.enums.NonLitigationActionType;
import hr.algebra.juristiq.enums.NonLitigationCaseType;
import hr.algebra.juristiq.models.TariffRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TariffRuleRepository extends JpaRepository<TariffRule, Long> {

    List<TariffRule> findByActionTypeAndLitigationCaseType(ActionType actionType, LitigationCaseType litigationCaseType);

    List<TariffRule> findByNonLitigationActionTypeAndNonLitigationCaseType(NonLitigationActionType nonLitigationActionType, NonLitigationCaseType nonLitigationCaseType);
}
