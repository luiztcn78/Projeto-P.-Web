package br.upe.parkgusmap.services.impl;

import br.upe.parkgusmap.Exeptions.*;
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
            throw new TipoDeDenunciaIndefinidoException();
        }
        if(denuncia.getDescricao() == null || denuncia.getDescricao().trim().isEmpty()){
            throw new DescricaoInvalidaException();
        }
        if(denuncia.getIdDenunciado() == null){
            throw new InexistenciaDoIdException(denuncia.getId());
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
            throw new DenunciaInexistenteException();
        }
        if(denuncia.getTipo() == TipoDenuncia.COMENTARIO){
            Long idDenunciado = denuncia.getIdDenunciado();
            return comentarioRepository.findById(idDenunciado).orElse(null);
        }
        throw new ObjetoDenunciadoNaoComentarioException();
    }

}
