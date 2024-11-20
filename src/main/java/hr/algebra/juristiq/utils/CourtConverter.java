package hr.algebra.juristiq.utils;

import hr.algebra.juristiq.enums.Court;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CourtConverter implements Converter<String, Court> {

    @Override
    public Court convert(String source) {
        return Court.fromString(source);
    }
}
