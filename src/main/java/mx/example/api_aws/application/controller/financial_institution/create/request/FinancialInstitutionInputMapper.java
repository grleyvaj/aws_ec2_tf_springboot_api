package mx.example.api_aws.application.controller.financial_institution.create.request;

import mx.example.api_aws.application.contract.Mapper;
import mx.example.api_aws.domain.use_case.create.FinancialInstitutionCreateInput;

public class FinancialInstitutionInputMapper implements Mapper<FinancialInstitutionCreateRequest, FinancialInstitutionCreateInput> {

    @Override
    public FinancialInstitutionCreateInput map(FinancialInstitutionCreateRequest input) {
        return new FinancialInstitutionCreateInput(
                input.getId(),
                input.getName(),
                input.getType()
        );
    }

}
