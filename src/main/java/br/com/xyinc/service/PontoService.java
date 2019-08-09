package br.com.xyinc.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.xyinc.model.Ponto;
import br.com.xyinc.repository.PontoRepository;

@Service
public class PontoService {

	@Autowired
	private PontoRepository pontoRepository;

	public Ponto atualizar(Integer codigo, Ponto ponto) {
		Ponto pontoBanco = buscarProdutoPeloCodigo(codigo);
		BeanUtils.copyProperties(ponto, pontoBanco, "codigo");
		return pontoRepository.save(pontoBanco);
	}

	public void atualizarPropriedadeAtivo(Integer codigo, Boolean ativo) {
		Ponto pontoBanco = buscarProdutoPeloCodigo(codigo);
		pontoRepository.save(pontoBanco);
	}

	private Ponto buscarProdutoPeloCodigo(Integer codigo) {
		Ponto pontoBanco = pontoRepository.findById(codigo).orElse(null);
		if (pontoBanco == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pontoBanco;
	}

}
