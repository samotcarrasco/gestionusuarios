package es.mdef.gestionusuarios.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.mdef.gestionusuarios.repositorios.UsuarioRepositorio;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private UsuarioRepositorio repositorio;
	
	public UserDetailsServiceImpl(UsuarioRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repositorio.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(
						"Usuario " + username + " no encontrado"));
	}

}
