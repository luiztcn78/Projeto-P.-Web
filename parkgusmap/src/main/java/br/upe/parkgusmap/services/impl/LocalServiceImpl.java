package br.upe.parkgusmap.services.impl;

import br.upe.parkgusmap.entities.Local;
import br.upe.parkgusmap.repositories.LocalRepository;
import br.upe.parkgusmap.services.LocalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalServiceImpl implements LocalService {

    @Autowired
    private LocalRepository localRepository;


    @Override
    public Local buscarLocalPorId(Long id) {
        return localRepository.findById(id).orElse(null);
    }

    @Override
    public List<Local> findAll() {
        return localRepository.findAll();
    }

    @Override
    public Optional<Local> findById(Long id) {
        return localRepository.findById(id);
    }

    @Override
    public Local save(Local local) {
        return localRepository.save(local);
    }

    @Override
    public Local update(Long id, Local local) {
        if (!localRepository.existsById(id)) {
            throw new RuntimeException("Local não encontrado com id: " + id);
        }
        local.setId(id);
        return localRepository.save(local);
    }

    @Override
    public void deleteById(Long id) {
        if (!localRepository.existsById(id)) {
            throw new RuntimeException("Local não encontrado com id: " + id);
        }
        localRepository.deleteById(id);
    }

    @Override
    public List<Local> findByAdministradorId(Long administradorId) {
        return localRepository.findByAdministradorId(administradorId);
    }

    @Override
    public List<Local> findByNomeContaining(String nome) {
        return localRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Override
    public List<Local> findByEnderecoContaining(String endereco) {
        return localRepository.findByEnderecoContainingIgnoreCase(endereco);
    }

    @Override
    public List<Local> findByNomeOrEnderecoContaining(String termo) {
        return localRepository.findByNomeOrEnderecoContaining(termo);
    }
}