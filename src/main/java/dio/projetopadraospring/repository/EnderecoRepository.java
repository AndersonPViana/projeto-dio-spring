package dio.projetopadraospring.repository;

import dio.projetopadraospring.model.Endereco;
import org.springframework.data.repository.CrudRepository;

public interface EnderecoRepository extends CrudRepository<Endereco, String> {
}
