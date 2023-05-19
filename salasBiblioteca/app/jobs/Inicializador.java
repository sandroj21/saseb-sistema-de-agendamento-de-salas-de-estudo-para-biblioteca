package jobs;

import enums.TipoSala;
import enums.TipoUsuario;
import models.Administrador;
import models.Aluno;
import models.Sala;
import models.Usuario;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.libs.Crypto;

@OnApplicationStart
public class Inicializador extends Job<Usuario> {

	@Override
	public void doJob() throws Exception {
		if(Usuario.count() == 0) {
			Administrador admin = new Administrador();
			admin.nome = "Admin";
			admin.email = "admin@admin.com";
			admin.matricula = 123;
			admin.senha = Crypto.passwordHash("12345");
			admin.tipo = TipoUsuario.ADMIN;
			admin.save();
			
			Sala sala = new Sala();
			sala.numeroIdentificacao = 1;
			sala.tipo = TipoSala.INDIVIDUAL;
			sala.save();
			
			Sala sala2 = new Sala();
			sala2.numeroIdentificacao = 2;
			sala2.tipo = TipoSala.GRUPO;
			sala2.save();
			
			Aluno aluno = new Aluno();
			aluno.nome = "Sandro Junior";
			aluno.email = "sandro@gmail.com";
			aluno.matricula = 34342;
			aluno.senha = Crypto.passwordHash("12345");
			aluno.tipo = TipoUsuario.COMUM;
			aluno.save();
			
			Aluno aluno2 = new Aluno();
			aluno2.nome = "Rafael Marques";
			aluno2.email = "rafael@gmail.com";
			aluno2.matricula = 98595;
			aluno2.senha = Crypto.passwordHash("12345");
			aluno2.tipo = TipoUsuario.COMUM;
			aluno2.save();
			
			Aluno aluno3 = new Aluno();
			aluno3.nome = "Eryck Fernandes";
			aluno3.email = "eryck@gmail.com";
			aluno3.matricula = 43984;
			aluno3.senha = Crypto.passwordHash("12345");
			aluno3.tipo = TipoUsuario.COMUM;
			aluno3.save();
			
		}
		
	}
}
