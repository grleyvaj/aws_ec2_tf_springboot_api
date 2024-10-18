package mx.example.api_aws.domain.use_case.create;

import lombok.RequiredArgsConstructor;
import mx.example.api_aws.domain.entity.FinancialInstitution;

@RequiredArgsConstructor
public class FinancialInstitutionCreateUseCase {

    public FinancialInstitution execute(FinancialInstitutionCreateInput createInput) {
        return new FinancialInstitution(
                createInput.getId(),
                createInput.getName(),
                createInput.getType()
        );
    }

}
