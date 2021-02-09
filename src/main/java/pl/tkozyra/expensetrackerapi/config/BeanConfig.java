package pl.tkozyra.expensetrackerapi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.tkozyra.expensetrackerapi.utils.StringToTransactionTypeConverter;

@Configuration
public class BeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public StringToTransactionTypeConverter stringToTransactionTypeConverter() {
        return new StringToTransactionTypeConverter();
    }
}
