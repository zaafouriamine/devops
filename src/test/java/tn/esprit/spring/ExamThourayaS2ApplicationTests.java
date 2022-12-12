package tn.esprit.spring;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Voyageur;
import tn.esprit.spring.repository.VoyageRepository;
import tn.esprit.spring.services.VoyageurServiceImpl;

@SpringBootTest	
@ExtendWith(MockitoExtension.class)
public class ExamThourayaS2ApplicationTests {

	private Voyageur voyageur ;
	@Mock
	VoyageRepository voyageurRepository=Mockito.mock(VoyageRepository.class);

	@InjectMocks
	VoyageurServiceImpl voageurService;

	Voyageur voyageur1 = new Voyageur(null, "amine",null);
	

	List<Voyageur> voyageurlist = new ArrayList<Voyageur>() {

		{
			add(new Voyageur(1L, "amine",null));
			add(new Voyageur(2L, "amine",null));
			add(new Voyageur(3L, "amine",null));
		}};

		
	@Test
	void addVoyageurTest() {
		System.out.println("***** ajouterVoyageur Test Mockito *****");
		for (Voyageur p:voyageurlist) {
			voageurService.ajouterVoyageur(p);
			verify(voyageurRepository).save(null);
		}
		System.out.println("***** ajouterVoyageur Test Mockito: success *****");
	}
	
	@Test
	public void recupererAllVoyageur()
	{
		System.out.println("***** recupererAllVoyageur Mockito *****");
		when(voageurService.recupererAll()).thenReturn(voyageurlist);
		List<Voyageur> voyageurlist1 = voageurService.recupererAll();
		Assertions.assertEquals(3, voyageurlist1.size());
		System.out.println(" retrieveAllProduitstest Mockito : success");

	}

}
