package mx.example.api_aws.application.exception;


public class FinancialInstitutionNotFoundException extends RuntimeException {
    public FinancialInstitutionNotFoundException(Long id) {
        super("No se encontró la institución financiera con ID: " + id);
    }
}