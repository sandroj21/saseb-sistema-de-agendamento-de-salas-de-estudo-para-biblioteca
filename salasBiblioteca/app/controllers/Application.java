package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.Administrador;
import models.Usuario;

@With(Seguranca.class)
public class Application extends Controller {

	@Before
	static void checarAdministrador() {

		if (session.get("usuario.email") != null && !session.get("tipoUsuario").equals("ADMIN")) {
			flash.error("Acesso restrito");
			Usuarios.index();
		}
	}
	
	public static void index() {
		render();
	}

}