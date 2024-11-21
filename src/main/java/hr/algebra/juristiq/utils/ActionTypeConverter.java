package hr.algebra.juristiq.utils;

import hr.algebra.juristiq.enums.ActionType;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ActionTypeConverter implements Converter<String, ActionType> {

    @Override
    public ActionType convert(String source) {
        return ActionType.fromString(source);
    }
}
