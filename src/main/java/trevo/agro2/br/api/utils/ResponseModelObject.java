package trevo.agro2.br.api.utils;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class ResponseModelObject extends ResponseModel{
    private Object objects;

    public ResponseModelObject(String message, Object objects){
        this.setMessage(message);
        this.setDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS").format(new Date()));
        this.setObjects(objects);
    }
}
