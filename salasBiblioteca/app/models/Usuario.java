package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import enums.TipoUsuario;
import play.data.validation.Email;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class Usuario extends Model {

	@Required(message="O nome é obrigatório")
	@MinSize(3)
	public String nome;
	
	@Required(message="Email obrigatório")
	@Email
	@Unique(message="O email informado já existe")
	public String email;
	
	@Required(message="O campo matricula é obrigatório")
	@MinSize(value=5, message="O tamanho mínimo para matricula é de 5 digitos")
	public int matricula;
	
	@Required(message="O campo senha é obrigatório")
	@MinSize(value=3, message="A senha tem que ter no mínimo 3 digitos")
	public String senha;
	
	public void setSenha() {
		this.senha = Crypto.passwordHash(senha);
	}
	
	@OneToMany
	@JoinTable(name="agendamentos_usuarios")
	public List<Agendamento> agendamentos;
	
	public TipoUsuario tipo;
}
