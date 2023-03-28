package trevo.agro2.br.api.exceptions.models;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
