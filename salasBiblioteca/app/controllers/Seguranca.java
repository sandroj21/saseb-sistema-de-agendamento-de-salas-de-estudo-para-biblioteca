package controllers;

import models.Admin;
import models.Administrador;
import models.Usuario;
import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller {

	@Before
	static void checarUsuarioLogado() {
		if (session.get("usuario.email") == null) {
			flash.error("Acesso restrito!");
			Login.fazerLogin();
		}

	}

	
}
