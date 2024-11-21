package hr.algebra.juristiq.utils;


import hr.algebra.juristiq.enums.NonLitigationCaseType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NonLitigationCaseTypeConverter implements Converter<String, NonLitigationCaseType> {

    @Override
    public NonLitigationCaseType convert(String source) {
        return NonLitigationCaseType.fromString(source);
    }
}
