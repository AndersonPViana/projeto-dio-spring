package dio.projetopadraospring.repository;

import dio.projetopadraospring.model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
}
