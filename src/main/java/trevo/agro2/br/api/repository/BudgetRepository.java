package trevo.agro2.br.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trevo.agro2.br.api.model.Budget;
import trevo.agro2.br.api.model.Product;

import java.util.List;
import java.util.UUID;

public interface BudgetRepository extends JpaRepository<Budget, UUID> {
    List<Budget> findByProducts(Product product);
    List<Budget> findByEmail(String email);

}
