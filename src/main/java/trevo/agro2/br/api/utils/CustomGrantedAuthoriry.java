package trevo.agro2.br.api.utils;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public class CustomGrantedAuthoriry implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 1L;

    private String authority;

    public CustomGrantedAuthoriry(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "CustomGrantedAuthority [authority=" + authority + "]";
    }
}
