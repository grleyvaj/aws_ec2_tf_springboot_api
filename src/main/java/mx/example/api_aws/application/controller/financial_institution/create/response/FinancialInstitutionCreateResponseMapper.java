package mx.example.api_aws.application.controller.financial_institution.create.response;

import mx.example.api_aws.application.contract.Mapper;
import mx.example.api_aws.domain.entity.FinancialInstitution;

public class FinancialInstitutionCreateResponseMapper implements Mapper<FinancialInstitution, FinancialInstitutionCreateResponse> {

    @Override
    public FinancialInstitutionCreateResponse map(FinancialInstitution entity) {
        return new FinancialInstitutionCreateResponse(
                entity.getId(),
                entity.getName(),
                entity.getType()
        );
    }

}
