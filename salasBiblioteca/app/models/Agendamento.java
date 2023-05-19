package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import enums.SituacaoAgendamento;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Agendamento extends Model{
	
	@Required(message="Campo obrigatório")
	@MinSize(value=3, message="Mínimo de 3 digitos")
	public String justificativa;
	
	@Required(message="Campo obrigatório")
	public String horario;

	public SituacaoAgendamento situacao;
	
	@Required(message="Campo obrigatório")
	@OneToOne
	public Sala sala;

	@ManyToOne
	public Usuario usuario;
}
