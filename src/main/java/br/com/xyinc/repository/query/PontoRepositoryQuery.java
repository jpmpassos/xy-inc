package br.com.xyinc.repository.query;

import java.math.BigDecimal;
import java.util.List;

import br.com.xyinc.model.Ponto;


public interface PontoRepositoryQuery {
	public List<Ponto> listarPorProximidade(Integer x, Integer y, BigDecimal distancia);
}
