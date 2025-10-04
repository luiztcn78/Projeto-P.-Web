package br.upe.parkgusmap.services;

import br.upe.parkgusmap.entities.Local;
import java.util.List;
import java.util.Optional;

public interface LocalService {

    Local buscarLocalPorId(Long id);

    List<Local> findAll();
    
    Optional<Local> findById(Long id);
    
    Local save(Local local);
    
    Local update(Long id, Local local);
    
    void deleteById(Long id);
    
    List<Local> findByAdministradorId(Long administradorId);
    
    List<Local> findByNomeContaining(String nome);
    
    List<Local> findByEnderecoContaining(String endereco);
    
    List<Local> findByNomeOrEnderecoContaining(String termo);
}