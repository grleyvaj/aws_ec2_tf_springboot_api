package mx.example.api_aws.application.controller.financial_institution.create.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FinancialInstitutionCreateRequest {

    @JsonProperty("id")
    @NotBlank(message = "REQUIRED")
    private Long id;

    @JsonProperty("name")
    @NotBlank(message = "REQUIRED")
    private String name;

    @JsonProperty("type")
    @NotBlank(message = "REQUIRED")
    private String type;

}
