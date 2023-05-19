package controllers;

import java.util.Arrays;
import java.util.List;

import enums.TipoSala;
import models.Agendamento;
import models.Sala;
import play.data.validation.Valid;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Salas extends Controller{

	@Before
	static void checarAdministrador() {

		if (session.get("usuario.email") != null && !session.get("tipoUsuario").equals("ADMIN")) {
			flash.error("Acesso restrito");
			Usuarios.index();
		}
	}
	
	public static void form() {
		List<TipoSala> tiposDeSalas = Arrays.asList(TipoSala.values());
		render(tiposDeSalas);
	}
	
	public static void salvar(@Valid Sala sala) {
		
		if(validation.hasErrors()) {
			params.flash();
			validation.keep();
			form();
		}
		if(sala.id == null) {
			flash.success("Sala cadastrada com sucesso!");
			sala.save();
		} else {
			flash.success("Sala editada com sucesso!");
			sala.save();
		}
		listar();
	}
	
	
	
	public static void listar() {
		List<Sala> listaSalas = Sala.findAll();
		render(listaSalas);
	}
	
	public static void editar(Long id) {
		Sala sala = Sala.findById(id);
		List<Sala> listaSalas = Sala.findAll();
		renderTemplate("Salas/form.html", sala, listaSalas);
	}
	
	public static void remover(Long id) {
		Sala sala = Sala.findById(id);
		sala.delete();
		flash.success("Sala removida com sucesso!");
		listar();
	}
	
	public static void detalhes(Long id) {
		Sala sala = Sala.findById(id);
		render(sala);
	}
}

