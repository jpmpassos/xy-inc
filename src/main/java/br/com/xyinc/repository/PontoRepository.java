package br.com.xyinc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.xyinc.model.Ponto;
import br.com.xyinc.repository.query.PontoRepositoryQuery;

public interface PontoRepository extends JpaRepository<Ponto, Integer>, PontoRepositoryQuery {

}
