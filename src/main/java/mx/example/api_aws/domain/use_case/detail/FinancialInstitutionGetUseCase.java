package mx.example.api_aws.domain.use_case.detail;

import lombok.RequiredArgsConstructor;
import mx.example.api_aws.domain.entity.FinancialInstitution;

import java.util.Optional;

@RequiredArgsConstructor
public class FinancialInstitutionGetUseCase {

    public Optional<FinancialInstitution> execute(Long id) {
        return Optional.of(
                new FinancialInstitution(
                        id,
                        "::name-fake::",
                        "::type-fake::"
                )
        );
    }
}
