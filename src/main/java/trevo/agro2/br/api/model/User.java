package trevo.agro2.br.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import trevo.agro2.br.api.dto.user.RoleEnum;
import trevo.agro2.br.api.utils.CustomGrantedAuthoriry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Table(name = "tb_user")
@Entity(name = "User")
@NoArgsConstructor
@JsonInclude
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    @Email(message = "Email inválido")
    @JsonProperty
    private String login;
    @Column(nullable = false)
    @JsonProperty
    private String password;
    @Enumerated(EnumType.STRING)
    @JsonProperty
    private RoleEnum role;
    @Column(name = "date", length = 50)
    private String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));


    public User(String name, String login, String password, RoleEnum role) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
    }


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new CustomGrantedAuthoriry("ROLE_" + role.toString()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void update(User dto) {
        if (dto.name != null) {
            this.name = dto.name;
        }
        if (dto.login != null) {
            this.login = dto.login;
        }
        if (dto.role != null) {
            this.role = dto.role;
        }
    }

}
