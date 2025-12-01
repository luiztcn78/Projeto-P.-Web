package br.upe.parkgusmap.services;

import br.upe.parkgusmap.entities.Usuario;

public interface TokenService {

    public String generateToken(Usuario usuario);

    public String validateToken(String token);
}
