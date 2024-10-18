package mx.example.api_aws.application.controller.financial_institution.detail.response;

import mx.example.api_aws.application.contract.Mapper;
import mx.example.api_aws.domain.entity.FinancialInstitution;

public class FinancialInstitutionDetailResponseMapper implements Mapper<FinancialInstitution, FinancialInstitutionDetailResponse> {

    @Override
    public FinancialInstitutionDetailResponse map(FinancialInstitution entity) {
        return new FinancialInstitutionDetailResponse(
                entity.getId(),
                entity.getName(),
                entity.getType()
        );
    }

}
