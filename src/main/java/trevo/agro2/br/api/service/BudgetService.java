package trevo.agro2.br.api.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.webjars.NotFoundException;
import trevo.agro2.br.api.dto.budget.BudgetDTO;
import trevo.agro2.br.api.exceptions.models.BadRequestException;
import trevo.agro2.br.api.model.Budget;
import trevo.agro2.br.api.model.Product;
import trevo.agro2.br.api.repository.BudgetRepository;
import trevo.agro2.br.api.repository.ProductRepository;
import trevo.agro2.br.api.utils.ResponseModel;
import trevo.agro2.br.api.utils.ResponseModelMessage;
import trevo.agro2.br.api.utils.ResponseModelObject;

import java.util.List;
import java.util.UUID;

@Service
public class BudgetService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BudgetRepository budgetRepository;

    public ResponseEntity<?> register(@RequestBody @Valid BudgetDTO dto) {
        List<Product> products = productRepository.findByIdIn(dto.productIds());
        Budget budget = new Budget(dto, products);
        budgetRepository.save(budget);

        return new ResponseEntity<>(new ResponseModelObject(dto.name().split(" ")[0] + " obrigado por solicitar um orçamento em nosso site!" +
                " Ficamos felizes em poder ajudá-lo e agradecemos pela confiança em nossos serviços." +
                " Para fornecer um orçamento preciso, precisamos avaliar suas necessidades com mais detalhes." +
                " Entraremos em contato em breve para obter mais informações e esclarecer quaisquer dúvidas." +
                " Nossa equipe está sempre disponível para ajudá-lo no que for preciso." +
                " Agradecemos novamente pela sua preferência e aguardamos ansiosamente seu retorno para seguir com a solicitação do orçamento." +
                " Atenciosamente, Trevo SA", products), HttpStatus.OK);
    }

    public ResponseEntity<?> list() {
        List<Budget> budgetList = budgetRepository.findAll();
        if (budgetList.isEmpty()) {
            throw new NotFoundException("Não existem orçamentos cadastrados");
        }
        return ResponseEntity.ok(new ResponseModelObject("Detalhes de todos os orçamentos", budgetList));
    }

    public ResponseEntity<?> detailsId(@PathVariable UUID id) {
        Budget budget = budgetRepository.findById(id).orElse(null);
        if (budget == null) {
            throw new NotFoundException("Nenhum orçamento com id " + id + " encontrado");
        }
        return new ResponseEntity<>(new ResponseModelObject("Detalhes do orçamento!", budget), HttpStatus.OK);
    }

    public ResponseEntity<?> detailsEmail(@PathVariable String email) {
        List<Budget> budgetList = budgetRepository.findByEmail(email);
        if (budgetList.isEmpty()) {
            throw new NotFoundException("Insira o nome completo do cliente e tente novamente");
        }
        return new ResponseEntity<>(new ResponseModelObject("Lista de pedidos: ", budgetList), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(@PathVariable UUID id) {
        Budget budget = budgetRepository.findById(id).orElse(null);
        if (budget == null) {
            throw new NotFoundException("Orçamento com id " + id + " não encontrado");
        }
        budgetRepository.deleteById(id);
        return new ResponseEntity<>(new ResponseModelMessage("Orçamento excluido!"), HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> update(@Valid @RequestBody BudgetDTO dto, @PathVariable UUID id) {
        List<Product> products = productRepository.findByIdIn(dto.productIds());
        Budget budget = budgetRepository.findById(id).orElse(null);
        if (!budgetRepository.existsById(id) || budget == null) {
            throw new BadRequestException("Orçamento com id " + id + " não encontrado!");
        }
        budget.update(dto, products);
        budgetRepository.save(budget);
        return new ResponseEntity<>(new ResponseModelMessage("Orçamento foi atualizado!"), HttpStatus.OK);
    }
}
