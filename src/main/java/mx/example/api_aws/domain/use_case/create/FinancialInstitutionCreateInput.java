package mx.example.api_aws.domain.use_case.create;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FinancialInstitutionCreateInput {

    private final Long id;
    private final String name;
    private final String type;

}
