package br.com.alura.forum.service;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.forum.dao.CategoriaDao;
import br.com.alura.forum.dao.TopicoDao;
import br.com.alura.forum.model.Categoria;
import br.com.alura.forum.model.vo.DashboardItem;

@ExtendWith(MockitoExtension.class)
class DashboardServiceTest {

	@InjectMocks
	private DashboardService dashboardService;
	
	@Mock
	private CategoriaDao categoriaDao;
	
	@Mock
	private TopicoDao topicoDao;
	
	private List<Categoria> categorias;
	
	@BeforeEach
	public void before() {
		Categoria backend = new Categoria("Back-End", null);
		Categoria frontend = new Categoria("Front-End", null);
		Categoria mobile = new Categoria("Mobile", null);
		
		this.categorias = Arrays.asList(backend,frontend, mobile);
	}
	
	@Test
	public void contadoresDeveriamEstarZeradosNoCasoDeNaoHaverTopicosAbertos() {
		Mockito.when(categoriaDao.buscarTodasAsCategoriasPrincipais()).thenReturn(categorias);
		categorias.forEach(c -> {
			Mockito.when(topicoDao.countPorCategoria(c)).thenReturn(0l);
			Mockito.when(topicoDao.countPorCategoriaEAbertosNaUltimaSemana(c)).thenReturn(0l);
			Mockito.when(topicoDao.countPorCategoriaENaoRespondidos(c)).thenReturn(0l);
		});
		
		List<DashboardItem> dashboard = dashboardService.buscarDadosDoDashboardDeTopicos();
		
		Assertions.assertEquals(3, dashboard.size());
		dashboard.forEach(d -> {
			Assertions.assertEquals(0, d.getQtdTopicos());
			Assertions.assertEquals(0, d.getQtdTopicosDaUltimaSemana());
			Assertions.assertEquals(0, d.getQtdTopicosNaoRespondidos());
		});
	}
	
}
