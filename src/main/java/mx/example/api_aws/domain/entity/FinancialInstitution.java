package mx.example.api_aws.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FinancialInstitution {

    private final Long id;
    private final String name;
    private final String type;

}
