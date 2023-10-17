package dio.projetopadraospring.service.impl;

import dio.projetopadraospring.model.Cliente;
import dio.projetopadraospring.model.Endereco;
import dio.projetopadraospring.repository.ClienteRepository;
import dio.projetopadraospring.repository.EnderecoRepository;
import dio.projetopadraospring.service.ClienteService;
import dio.projetopadraospring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Integer id) throws ChangeSetPersister.NotFoundException {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if( cliente == null) {
            throw new ChangeSetPersister.NotFoundException();
        }
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Integer id, Cliente cliente) {
        Optional<Cliente> clienteUp = clienteRepository.findById(id);
        if(clienteUp.isPresent()) {
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Integer id) {
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}
