package br.com.xyinc.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.xyinc.model.Ponto;
import br.com.xyinc.repository.PontoRepository;
import br.com.xyinc.util.FuncoesUtils;

@Service
public class PontoService {

	@Autowired
	private PontoRepository pontoRepository;

	public Ponto salvar(Ponto ponto) {
		return pontoRepository.save(ponto);
	}

	public Ponto atualizar(Integer codigo, Ponto ponto) {
		Ponto pontoBanco = buscarPontoPeloCodigo(codigo);
		BeanUtils.copyProperties(ponto, pontoBanco, "pontoid");
		return pontoRepository.save(pontoBanco);
	}

	public Ponto buscarPontoPeloCodigo(Integer codigo) {
		Ponto pontoBanco = pontoRepository.findById(codigo).orElse(null);
		if (pontoBanco == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pontoBanco;
	}

	public List<Ponto> listarTodos() {
		return pontoRepository.findAll();
	}

	public void deletar(Integer codigo) {
		pontoRepository.deleteById(codigo);
	}

	public List<Ponto> listarPorProximidadeBanco(Integer x, Integer y, BigDecimal distancia) {
		return pontoRepository.listarPorProximidade(x, y, distancia);
	}

	public List<Ponto> listarPorProximidadeJava(Integer x, Integer y, BigDecimal distancia) {
		List<Ponto> pontos = pontoRepository.findAll();

		return pontos.stream().filter((Ponto p) -> 
					FuncoesUtils.caucularDistanciaDeCoodenadas(x, y, p.getCoordenadax(), p.getCoordenaday()) <= distancia.doubleValue()
				).collect(Collectors.toList());
	}

}
