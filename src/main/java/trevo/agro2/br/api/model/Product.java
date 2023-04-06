package trevo.agro2.br.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trevo.agro2.br.api.dto.Category;
import trevo.agro2.br.api.dto.ProductDto;
import trevo.agro2.br.api.dto.Status;
import java.time.LocalDate;
import java.util.UUID;


@Table(name = "tb_product")
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "description",nullable = false)
    private String description;
    @Column(name = "price",nullable = false)
    private Double price;
    private LocalDate date;
    private Status status;
    private Category category;

    public Product(ProductDto dto){
        this.name = dto.name();
        this.description = dto.description();
        this.price = dto.price();
        this.date = LocalDate.now();
        this.status = Status.DISPONIVEL;
        this.category = dto.category();
    }

    public void update(ProductDto dto) {
        if (dto.name() != null) {
            this.name = dto.name();
        }
        if (dto.description() != null) {
            this.description = dto.description();
        }
        if (dto.price() != null){
            this.price = dto.price();
        }
        if (dto.status() != null ){
            this.status = dto.status();
        }
        if (dto.category() != null) {
            this.category = dto.category();
        }
    }
}
