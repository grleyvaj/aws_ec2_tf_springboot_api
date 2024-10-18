package mx.example.api_aws.application.contract;

public interface Mapper<From, To> {

    To map(From input);

}