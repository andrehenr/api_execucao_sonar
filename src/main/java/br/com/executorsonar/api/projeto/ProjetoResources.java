package br.com.executorsonar.api.projeto;

import java.io.File;
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

import br.com.executorsonar.api.utils.Descompactador;

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

				localizacaoArquivo = salvaArquivoEmPastaLocal(anexo);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(localizacaoArquivo.toString());

		} else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body("Arquivo precisa estar no formato application/zip");
		}

	}

	private Path salvaArquivoEmPastaLocal(MultipartFile anexo) throws IOException {
		String nome = anexo.getOriginalFilename();
		Path localizacaoArquivo;
		
		String pastaLocalOndeArquivosSeraoSalvos = "C://Users//"+System.getProperty("user.name")+"//Desktop//anexos//";
		
		if(!new File(pastaLocalOndeArquivosSeraoSalvos).exists()){
			new File(pastaLocalOndeArquivosSeraoSalvos).mkdir();
		}
		localizacaoArquivo = Paths.get(pastaLocalOndeArquivosSeraoSalvos + nome);
		anexo.transferTo(localizacaoArquivo);
		Descompactador.unZipIt(localizacaoArquivo.toString(), pastaLocalOndeArquivosSeraoSalvos);
		return localizacaoArquivo;
	}

	@PostMapping
	public ResponseEntity<ProjetoEntity> salvarProjeto(@Valid @RequestBody ProjetoEntity projeto) {

		ProjetoEntity projetoSalvo = projetoService.save(projeto);
		
		

		return ResponseEntity.status(HttpStatus.CREATED).body(projetoSalvo);
	}

}
