package hr.algebra.juristiq.utils;

import hr.algebra.juristiq.enums.ActionType;
import hr.algebra.juristiq.enums.NonLitigationActionType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NonLitigationActionTypeConverter implements Converter<String, NonLitigationActionType> {

    @Override
    public NonLitigationActionType convert(String source) {
        return NonLitigationActionType.fromString(source);
    }
}
