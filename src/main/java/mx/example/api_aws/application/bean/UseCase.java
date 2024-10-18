package mx.example.api_aws.application.bean;

import mx.example.api_aws.domain.use_case.create.FinancialInstitutionCreateUseCase;
import mx.example.api_aws.domain.use_case.detail.FinancialInstitutionGetUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCase {

    @Bean
    public FinancialInstitutionCreateUseCase financialInstitutionCreateUseCase() {
        return new FinancialInstitutionCreateUseCase();
    }

    @Bean
    public FinancialInstitutionGetUseCase financialInstitutionGetUseCase() {
        return new FinancialInstitutionGetUseCase();
    }

}
