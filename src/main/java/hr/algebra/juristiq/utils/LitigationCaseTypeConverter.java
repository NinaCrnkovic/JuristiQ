package hr.algebra.juristiq.utils;

import hr.algebra.juristiq.enums.Court;
import hr.algebra.juristiq.enums.LitigationCaseType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LitigationCaseTypeConverter implements Converter<String, LitigationCaseType> {

    @Override
    public LitigationCaseType convert(String source) {
        return LitigationCaseType.fromString(source);
    }
}
