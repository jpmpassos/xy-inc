package br.com.xyinc.resource;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.xyinc.event.RecursoCriadoEvent;
import br.com.xyinc.model.Ponto;
import br.com.xyinc.repository.PontoRepository;
import br.com.xyinc.service.PontoService;

@RestController
@RequestMapping("/ponto")
public class PontoResource {

	@Autowired
	private PontoRepository pontoRepository;
	@Autowired
	private ApplicationEventPublisher publisher;
	@Autowired
	private PontoService pontoService;

	@CrossOrigin
	@GetMapping
	public List<Ponto> listar() {
		return pontoRepository.findAll();
	}

	@CrossOrigin
	@PostMapping
	public ResponseEntity<Ponto> cria(@Valid @RequestBody Ponto ponto, HttpServletResponse response) {
		Ponto pontoSalva = pontoRepository.save(ponto);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pontoSalva.getPontoid()));

		return ResponseEntity.status(HttpStatus.CREATED).body(pontoSalva);
	}

	@CrossOrigin
	@PutMapping("{codigo}")
	public ResponseEntity<Ponto> atualizar(@PathVariable Integer codigo, @RequestBody Ponto ponto) {

		return ResponseEntity.ok(pontoService.atualizar(codigo, ponto));
	}

	@CrossOrigin
	@GetMapping("/{codigo}")
	public ResponseEntity<Ponto> buscarPeloCodigo(@PathVariable Integer codigo, HttpServletResponse response) {
		Ponto ponto = pontoRepository.findById(codigo).orElse(null);

		if (ponto == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(ponto);
	}

	@CrossOrigin
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer codigo) {
		pontoRepository.deleteById(codigo);
	}
	
	@CrossOrigin
	@Transactional
	public List<Ponto> listarTodos() {	
		return pontoRepository.findAll();
	}
	
	@CrossOrigin
	@GetMapping("porproximidade")
	@Transactional
	public List<Ponto> listarPaginado(Integer x, Integer y, BigDecimal distancia) {	
		return pontoRepository.listarPorProximidade(x, y, distancia);
	}

}