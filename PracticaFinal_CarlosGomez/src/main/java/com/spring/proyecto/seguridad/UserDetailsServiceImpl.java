package com.spring.proyecto.seguridad;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.spring.proyecto.usuario.Usuario;
import com.spring.proyecto.usuario.UsuarioDAO;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Usuario> usuario = usuarioDAO.findById(username);
		if(usuario.isPresent()) {
			
			return (UserDetails)usuario.get();
		}
		
		throw new UsernameNotFoundException(username);
	}
}