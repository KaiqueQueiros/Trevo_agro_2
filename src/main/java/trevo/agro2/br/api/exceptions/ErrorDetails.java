package trevo.agro2.br.api.exceptions;

import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@ToString
public class ErrorDetails {
    private final Integer status;
    private final Object message;

    public ErrorDetails(Integer status,Object message){
        super();
        this.status = status;
        this.message = message;
    }

    public ErrorDetails(Integer status, Map<String, List<String>> erros){
        super();

        this.status = status;
        this.message = erros;
    }
}
