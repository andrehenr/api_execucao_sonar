package br.com.executorsonar.api.projeto;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjetoService {

	@Autowired
	private ProjetoRepository projetoRepository;

	public ProjetoEntity save(@Valid ProjetoEntity projeto) {
		return projetoRepository.save(projeto);
	}

}
