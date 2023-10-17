package dio.projetopadraospring.service;

import dio.projetopadraospring.model.Cliente;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface ClienteService {
    Iterable<Cliente> buscarTodos();
    Cliente buscarPorId(Integer id) throws ChangeSetPersister.NotFoundException;

    void inserir(Cliente cliente);

    void atualizar(Integer id, Cliente cliente);

    void deletar(Integer id);
}
