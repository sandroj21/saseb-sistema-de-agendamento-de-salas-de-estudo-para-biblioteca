package controllers;

import java.util.List;

import enums.TipoUsuario;
import models.Aluno;
import play.data.validation.Valid;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Alunos extends Controller{
	
	@Before
	static void checarAdministrador() {

		if (session.get("usuario.email") != null && !session.get("tipoUsuario").equals("ADMIN")) {
			flash.error("Acesso restrito");
			Usuarios.index();
		}
	}
	 
	public static void form() {
		render();
	}
	
	public static void salvar(@Valid Aluno alu) {
		
		if(validation.hasErrors()) {
			params.flash();
			validation.keep();
			form();
		}
		
		alu.tipo = TipoUsuario.COMUM;
		
		if(alu.id == null) {
			flash.success("Aluno cadastrado com sucesso!");
			alu.setSenha();
			alu.save();
		} else {
			flash.success("Aluno editado com sucesso!");
			alu.save();
		}
		listar();
	}
	
	public static void listar() {
		List<Aluno> listaAlunos = Aluno.findAll();
		render(listaAlunos);
	}
	
	public static void editar(Long id) {
		Aluno alu = Aluno.findById(id);
		renderTemplate("Alunos/form.html", alu);
	}
	
	public static void remover(Long id) {
		Aluno alu = Aluno.findById(id);
		alu.delete();
		flash.success("Aluno removido com sucesso!");
		listar();
	}
	
	public static void detalhes(Long id) {
		Aluno alu = Aluno.findById(id);
		
		render(alu);
	}
}
