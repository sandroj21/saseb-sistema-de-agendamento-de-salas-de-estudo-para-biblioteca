package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import enums.TipoSala;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;

@Entity
public class Sala extends Model{

	@Required(message="Campo Obrigatório")
	@Unique(message="O número informado já existe")
	@MinSize(value=1)
	public int numeroIdentificacao;
	
	@Required(message="Campo Obrigatório")
	public TipoSala tipo;
	
	
}
