package br.com.executorsonar.api.projeto;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/projeto")
public class ProjetoResources {

	@Autowired
	private ProjetoService projetoService;

	@PostMapping("/anexo")
	public ResponseEntity<String> upload(@RequestParam MultipartFile anexo) {

		if (anexo.getContentType().equals("application/zip")) {

			String nome = null;
			Path localizacaoArquivo = null;
			try {

				nome = anexo.getOriginalFilename();
				localizacaoArquivo = Paths.get("C://Users//andre.graca//Desktop//anexos//" + nome);
				anexo.transferTo(localizacaoArquivo);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(localizacaoArquivo.toString());

		} else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body("Arquivo precisa estar no formato application/zip");
		}

	}

	@PostMapping
	public ResponseEntity<ProjetoEntity> salvarProjeto(@Valid @RequestBody ProjetoEntity projeto) {

		ProjetoEntity projetoSalvo = projetoService.save(projeto);

		return ResponseEntity.status(HttpStatus.CREATED).body(projetoSalvo);
	}

}
