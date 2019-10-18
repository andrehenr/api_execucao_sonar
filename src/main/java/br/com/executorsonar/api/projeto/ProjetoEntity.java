package br.com.executorsonar.api.projeto;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Entity
@Table(name = "projeto")
public class ProjetoEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long codigo;

	@EqualsAndHashCode.Exclude
	private String chavePainel;

	@EqualsAndHashCode.Exclude
	@Embedded
	private ConfiguracaoSonar sonar;

	@EqualsAndHashCode.Exclude
	private String nomeProjeto;

	@EqualsAndHashCode.Exclude
	private String caminhoProjeto;
	
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getChavePainel() {
		return chavePainel;
	}

	public void setChavePainel(String chavePainel) {
		this.chavePainel = chavePainel;
	}

	public ConfiguracaoSonar getSonar() {
		return sonar;
	}

	public void setSonar(ConfiguracaoSonar sonar) {
		this.sonar = sonar;
	}

	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	public String getCaminhoProjeto() {
		return caminhoProjeto;
	}

	public void setCaminhoProjeto(String caminhoProjeto) {
		this.caminhoProjeto = caminhoProjeto;
	}

}
