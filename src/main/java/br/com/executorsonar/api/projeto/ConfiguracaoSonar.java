package br.com.executorsonar.api.projeto;

import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.URL;

import lombok.Getter;
import lombok.Setter;

@Embeddable
public class ConfiguracaoSonar {
	
	@Setter
	@Getter
	public @URL String linkSonar;
	
	@Getter
	@Setter
	public String passSonar;
	
	@Getter
	@Setter
	public String loginSonar;

}