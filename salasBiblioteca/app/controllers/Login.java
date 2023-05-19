package controllers;

import java.util.Arrays;
import java.util.List;

import enums.TipoUsuario;
import models.Administrador;
import models.Aluno;
import models.Usuario;
import play.data.validation.Valid;
import play.libs.Crypto;
import play.mvc.Controller;

public class Login extends Controller {

	public static void fazerLogin() {
		render();
	}
	
	public static void cadastro() {
		render();
		
	}
	
	public static void salvarUsuario(@Valid Usuario usuario) {
		
		if(validation.hasErrors()) {
			params.flash();
			validation.keep();
			cadastro();
		}
		usuario.tipo = TipoUsuario.COMUM;
		
		usuario.setSenha();
		usuario.save();
		flash.success("Usuario cadastrado com sucesso");
		fazerLogin();
		
	}
	public static void autenticar(String email, String senha) {
		Usuario usuario = Usuario.find("email = ?1 and senha = ?2", email, Crypto.passwordHash(senha)).first();
		
		if(usuario != null) {
			session.put("usuario.nome", usuario.nome);
			session.put("usuario.email", usuario.email);
			session.put("usuario.id", usuario.id);
			session.put("tipoUsuario", usuario.tipo);
			
			if(usuario instanceof Administrador) {
				System.out.println("você logou como admin");
				Application.index();
			} else if(usuario instanceof Aluno || usuario instanceof Usuario){
				System.out.println("você logou como aluno");
				Usuarios.index();
			}
		} else {
			flash.error("Usuário ou senha inválidos");
			fazerLogin();
		}
	}
	
	public static void sair() {
		session.clear();
		fazerLogin();
	}
	
}
