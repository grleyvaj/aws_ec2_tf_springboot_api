package mx.example.api_aws.application.controller.financial_institution;

import lombok.RequiredArgsConstructor;
import mx.example.api_aws.application.contract.Mapper;
import mx.example.api_aws.application.controller.financial_institution.create.request.FinancialInstitutionCreateRequest;
import mx.example.api_aws.application.controller.financial_institution.create.response.FinancialInstitutionCreateResponse;
import mx.example.api_aws.application.controller.financial_institution.detail.response.FinancialInstitutionDetailResponse;
import mx.example.api_aws.application.controller.financial_institution.list.FinancialInstitutionDetailListResponse;
import mx.example.api_aws.application.exception.FinancialInstitutionNotFoundException;
import mx.example.api_aws.domain.entity.FinancialInstitution;
import mx.example.api_aws.domain.use_case.create.FinancialInstitutionCreateInput;
import mx.example.api_aws.domain.use_case.create.FinancialInstitutionCreateUseCase;
import mx.example.api_aws.domain.use_case.detail.FinancialInstitutionGetUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/financials")
public class FinancialInstitutionApiController {

    private final FinancialInstitutionCreateUseCase financialInstitutionCreateUseCase;
    private final FinancialInstitutionGetUseCase financialInstitutionGetUseCase;
    private final Mapper<FinancialInstitutionCreateRequest, FinancialInstitutionCreateInput> financialInstitutionCreateInputMapper;
    private final Mapper<FinancialInstitution, FinancialInstitutionCreateResponse> financialInstitutionCreateResponseMapper;
    private final Mapper<FinancialInstitution, FinancialInstitutionDetailResponse> financialInstitutionDetailResponseMapper;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<FinancialInstitutionCreateResponse> create(
            @RequestBody FinancialInstitutionCreateRequest createRequest
    ) {
        FinancialInstitutionCreateResponse response = this.financialInstitutionCreateResponseMapper.map(
                this.financialInstitutionCreateUseCase.execute(
                        this.financialInstitutionCreateInputMapper.map(createRequest)
                )
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<FinancialInstitutionDetailResponse> getDetail(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                this.financialInstitutionGetUseCase.execute(id).map(
                        this.financialInstitutionDetailResponseMapper::map
                ).orElseThrow(() -> new FinancialInstitutionNotFoundException(id) )
        );
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<FinancialInstitutionDetailListResponse> getAll() {
        FinancialInstitutionDetailResponse financialInstitutionDetailResponse = this.financialInstitutionGetUseCase.execute(2L).map(
                this.financialInstitutionDetailResponseMapper::map
        ).orElseThrow(() -> new FinancialInstitutionNotFoundException(2L));

        return ResponseEntity.ok(new FinancialInstitutionDetailListResponse(
                Collections.singletonList(financialInstitutionDetailResponse)
        ));
    }

}
