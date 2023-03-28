package trevo.agro2.br.api.exceptions.models;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
