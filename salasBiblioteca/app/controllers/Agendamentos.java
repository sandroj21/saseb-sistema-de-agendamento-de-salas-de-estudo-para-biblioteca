package controllers;

import java.util.List;

import enums.SituacaoAgendamento;
import models.Agendamento;

import models.Sala;
import models.Usuario;
import play.data.validation.Valid;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Agendamentos extends Controller{

	@Before
	static void checarAdministrador() {

		if (session.get("usuario.email") != null && !session.get("tipoUsuario").equals("ADMIN")) {
			flash.error("Acesso restrito");
			Usuarios.index();
		}
	}
	
	public static void form() {
		List<Sala> salas = Sala.findAll();
		List<Usuario> usuarios = Usuario.findAll();
		render(salas, usuarios);
	}
	
	public static void salvar(@Valid Agendamento agenda) {
		
		if(validation.hasErrors()) {
			params.flash();
			validation.keep();
			form();
		}
		
		if(agenda.usuario.id == null) {
			Usuario usuario = Usuario.findById(agenda.usuario.id);
		
			agenda.usuario = usuario;
		}
		agenda.situacao = SituacaoAgendamento.AGUARDANDO;
		if(agenda.id == null) {
			flash.success("Agendamento cadastrado com sucesso!");
			agenda.save();
		} else {
			flash.success("Agendamento editado com sucesso!");
			agenda.save();
		}
		listar();
		
	}
	
	public static void listar() {
		List<Agendamento> agendamentos = Agendamento.findAll();
		render(agendamentos);
	}
	
	public static void editar(Long id) {
		Agendamento agenda = Agendamento.findById(id);
		List<Sala> salas = Sala.findAll();
		List<Usuario> usuarios = Usuario.findAll();
		renderTemplate("agendamentos/form.html", agenda, salas, usuarios);
	}
	
	public static void remover(Long id) {
		Agendamento agenda = Agendamento.findById(id);
		agenda.delete();
		flash.success("Agendamento removido com sucesso!");
		listar();
		
	}
	
	public static void detalhes(Long id) {
		Agendamento agenda = Agendamento.findById(id);
		render(agenda);
	}
	
	public static void deferir(Long id) {
		Agendamento agenda = Agendamento.findById(id);
		
		agenda.situacao = SituacaoAgendamento.DEFERIDO;
		
		agenda.save();
		
		listar();
	}
	
	public static void indeferir(Long id) {
		Agendamento agenda = Agendamento.findById(id);
		
		agenda.situacao = SituacaoAgendamento.INDEFERIDO;
		
		agenda.save();
		
		listar();
	}
	
}
