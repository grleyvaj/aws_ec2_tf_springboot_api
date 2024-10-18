package mx.example.api_aws.application.bean;

import mx.example.api_aws.application.contract.Mapper;
import mx.example.api_aws.application.controller.financial_institution.create.request.FinancialInstitutionInputMapper;
import mx.example.api_aws.application.controller.financial_institution.create.response.FinancialInstitutionCreateResponse;
import mx.example.api_aws.application.controller.financial_institution.create.response.FinancialInstitutionCreateResponseMapper;
import mx.example.api_aws.application.controller.financial_institution.detail.response.FinancialInstitutionDetailResponse;
import mx.example.api_aws.application.controller.financial_institution.detail.response.FinancialInstitutionDetailResponseMapper;
import mx.example.api_aws.domain.entity.FinancialInstitution;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mappers {

    @Bean
    public FinancialInstitutionInputMapper financialInstitutionInputMapper() {
        return new FinancialInstitutionInputMapper();
    }

    @Bean
    public Mapper<FinancialInstitution, FinancialInstitutionCreateResponse> financialInstitutionCreateResponseMapper() {
        return new FinancialInstitutionCreateResponseMapper();
    }


    @Bean
    public Mapper<FinancialInstitution, FinancialInstitutionDetailResponse> financialInstitutionFinancialInstitutionDetailResponseMapper() {
        return new FinancialInstitutionDetailResponseMapper();
    }

}
