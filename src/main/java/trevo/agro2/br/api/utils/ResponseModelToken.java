package trevo.agro2.br.api.utils;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class ResponseModelToken {
    private String date;
    private Object token;
    public ResponseModelToken(Object token){
        this.setDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS").format(new Date()));
        this.setToken(token);
    }
}
