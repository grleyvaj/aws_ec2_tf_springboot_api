
package mx.example.api_aws.application.controller.financial_institution.list;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import mx.example.api_aws.application.controller.financial_institution.detail.response.FinancialInstitutionDetailResponse;

import java.util.List;

@Data
@Accessors(chain = true)
public class FinancialInstitutionDetailListResponse {

    @JsonProperty("id")
    private final List<FinancialInstitutionDetailResponse> financials;

}
