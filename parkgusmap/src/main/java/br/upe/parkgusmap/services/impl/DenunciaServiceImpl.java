package br.upe.parkgusmap.services.impl;

import br.upe.parkgusmap.entities.Comentario;
import br.upe.parkgusmap.entities.Denuncia;
import br.upe.parkgusmap.entities.Enums.TipoDenuncia;
import br.upe.parkgusmap.repositories.ComentarioRepository;
import br.upe.parkgusmap.repositories.DenunciaRepository;
import br.upe.parkgusmap.services.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DenunciaServiceImpl implements DenunciaService{
    @Autowired
    private DenunciaRepository denunciaRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public void fazerDenuncia(Denuncia denuncia) {

        if(denuncia.getTipo() == null){
            throw new IllegalArgumentException("O tipo da denúncia não foi definido.");
        }
        if(denuncia.getDescricao() == null || denuncia.getDescricao().trim().isEmpty()){
            throw new IllegalArgumentException("Escreva o motivo da denúncia.");
        }
        if(denuncia.getIdDenunciado() == null){
            throw new IllegalArgumentException("O Id que você está denunciando não existe.");
        }
        denunciaRepository.save(denuncia);
    }

    @Override
    public List<Denuncia> listarTodasDenuncias() {
        return denunciaRepository.findAll();
    }

    @Override
    public Comentario encontrarComentarioDenunciado(Long IdDenuncia) {
        Denuncia denuncia = denunciaRepository.findById(IdDenuncia).orElse(null);
        if(denuncia == null){
            throw new IllegalArgumentException("Denuncia não existente.");
        }
        if(denuncia.getTipo() == TipoDenuncia.COMENTARIO){
            Long idDenunciado = denuncia.getIdDenunciado();
            return comentarioRepository.findById(idDenunciado).orElse(null);
        }
        throw new IllegalArgumentException("O objeto denunciado não foi um comentário.");
    }

}
