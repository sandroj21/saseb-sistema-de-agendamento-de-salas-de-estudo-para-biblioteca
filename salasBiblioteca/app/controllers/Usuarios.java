package controllers;

import java.util.List;

import javax.mail.Session;

import enums.SituacaoAgendamento;
import models.Agendamento;
import models.Sala;
import models.Usuario;
import play.data.validation.Valid;
import play.libs.Crypto;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Usuarios extends Controller {
	
	public static void index() {
		render();
	}
	
	public static void cadastroAgendamento() {
		List<Sala> salas = Sala.findAll();
		render(salas);
	}
	
	public static void listaAgendamento() {
		
		Long idUsuario = Long.parseLong(session.get("usuario.id"));
		Usuario usuarioLogado = Usuario.findById(idUsuario);
		List<Agendamentos> agendamentos = Agendamento.find("usuario = ?", usuarioLogado).fetch();
		render(agendamentos);
	}
	
	public static void salvarAgendamento(@Valid Agendamento agenda) {
		
		if(validation.hasErrors()) {
			params.flash();
			validation.keep();
			cadastroAgendamento();
		}
		
		Long idUsuarioLogado = Long.parseLong(session.get("usuario.id"));
		
		Usuario usuario = Usuario.find("id = ?", idUsuarioLogado).first();
		
		agenda.situacao = SituacaoAgendamento.AGUARDANDO;
		
		if(usuario.id != null) {
			agenda.usuario = usuario;
			
			if(agenda.id == null) {
				flash.success("Agendamento cadastrado com sucesso!");
				agenda.save();
			} else {
				flash.success("Agendamento editado com sucesso!");
				agenda.save();
			}
			listaAgendamento();
		} 
		
	}
	
	
	public static void editarAgendamento(Long id) {
		Agendamento agenda = Agendamento.findById(id);
		List<Sala> salas = Sala.findAll();
		renderTemplate("Usuarios/cadastroAgendamento.html", agenda, salas);
	}
	
	public static void removerAgendamento(Long id) {
		Agendamento agenda = Agendamento.findById(id);
		flash.success("Agendamento removido com sucesso!");
		agenda.delete();
		listaAgendamento();
	}
}
