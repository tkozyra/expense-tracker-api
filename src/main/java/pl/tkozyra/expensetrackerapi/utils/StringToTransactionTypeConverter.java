package pl.tkozyra.expensetrackerapi.utils;

import org.springframework.core.convert.converter.Converter;

public class StringToTransactionTypeConverter implements Converter<String, TransactionType> {
    @Override
    public TransactionType convert(String source) {
        return TransactionType.valueOf(source.toUpperCase());
    }
}
