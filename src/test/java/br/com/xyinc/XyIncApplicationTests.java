package br.com.xyinc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.xyinc.model.Ponto;
import br.com.xyinc.repository.PontoRepository;
import br.com.xyinc.service.PontoService;
import br.com.xyinc.util.FuncoesUtils;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class XyIncApplicationTests {
	
	@Mock
	PontoRepository pontoRepository;

	@InjectMocks
	private PontoService pontoService;
	
	@Before
	public void before() {
		when(pontoRepository.findAll()).thenReturn(Arrays.asList(
				new Ponto(1, "Lanchonete", 27, 12),
				new Ponto(2, "Posto", 31, 18),
				new Ponto(3, "Joalheria", 15, 12),
				new Ponto(4, "Floricultura", 19, 21),
				new Ponto(5, "Pub", 12, 8),
				new Ponto(6, "Supermercado", 23, 6),
				new Ponto(7, "Churrascaria", 28, 2)				
			));
	}

	@Test
	public void testCaucularDistanciaDeCoodenadas() {
	
		double distanciaTeste = FuncoesUtils.caucularDistanciaDeCoodenadas(20, 10, 28, 2);

		assertEquals(distanciaTeste, 11.3137084989848, 0.0001);		
		
	}
	
	@Test
	public void testListarPorProximidadeJava() {
		
		List<Ponto> listTeste = pontoService.listarPorProximidadeJava(20, 10, new BigDecimal(10));
		
		List<Ponto> listResult = Arrays.asList(
					new Ponto(1, "Lanchonete", 27, 12),
					new Ponto(3, "Joalheria", 15, 12),
					new Ponto(5, "Pub", 12, 8),
					new Ponto(6, "Supermercado", 23, 6)
				);
	
		assertArrayEquals(listTeste.toArray(), listResult.toArray());		
		
	}


}

