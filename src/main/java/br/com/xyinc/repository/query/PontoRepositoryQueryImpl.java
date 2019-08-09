package br.com.xyinc.repository.query;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.xyinc.model.Ponto;

public class PontoRepositoryQueryImpl implements PontoRepositoryQuery {

	// @PersistenceContext
	// private EntityManager entityManager;
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public List<Ponto> listarPorProximidade(Integer x, Integer y, BigDecimal distancia) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		StringBuffer buffer = new StringBuffer();
		try {
			entityManager.getTransaction().begin();

			buffer.append(" SELECT * FROM ponto p ");
			buffer.append(" where sqrt((p.\"coordenadax\" - :x )*(p.\"coordenadax\" - :x )+(p.\"coordenaday\" -:y )*(p.\"coordenaday\" - :y )) <= :distancia ");

			Query query = entityManager.createNativeQuery(buffer.toString(), Ponto.class);
			query.setParameter("x", x);
			query.setParameter("y", y);
			query.setParameter("distancia", distancia);

			List<Ponto> list = query.getResultList();

			entityManager.getTransaction().commit();
			return list;
		} finally {
			entityManager.close();
		}
	}

}
