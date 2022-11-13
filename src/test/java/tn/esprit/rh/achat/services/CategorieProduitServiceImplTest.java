package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.ICategorieProduitService;

import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CategorieProduitServiceImplTest {

	@Autowired
	ICategorieProduitService cs;



	@MockBean
    	private CategorieProduitRepository cr;
	
	CategorieProduit c1=new CategorieProduit(1L,"cat 1","cat 1",null);
	CategorieProduit c2=new CategorieProduit(2L,"cat 1","cat 1",null);

	@Test
    	public void retrieveAllCategorieProduitsTest() {
        when(cr.findAll()).thenReturn(Stream
                .of(c1,c2)
                .collect(Collectors.toList()));
        assertEquals(2,cs.retrieveAllCategorieProduits().size());
        System.out.println("Retrieve All CategorieProduits");
    }

    @Test
    public void addCategorieProduitTest() {
        when(cr.save(c1)).thenReturn(c1);
        assertNotNull(c1);
        assertEquals(c1, cs.addCategorieProduit(c1));
        System.out.println("Add CategorieProduit");
    }


    @Test
    public void DeleteCategorieProduitTest() {
        cr.save(c1);
        cs.deleteCategorieProduit(c1.getIdCategorieProduit());
        verify(pr, times(1)).deleteById(c1.getIdCategorieProduit());
        System.out.println("Delete CategorieProduit");

    }

    @Test
    public void CategorieProduitTest() {
        when(cr.findById(c1.getIdCategorieProduit())).thenReturn(Optional.of(c1));
        assertEquals(c1, cs.retrieveCategorieProduit(c1.getIdCategorieProduit()));
        System.out.println("Retrieve CategorieProduit");
    }


    @Test
    public void UpdateCategorieProduitTest() {
        when(cr.save(c1)).thenReturn(c1);
        assertNotNull(c1);
        assertEquals(c1, cs.updateCategorieProduit(c1));
        System.out.println("Update CategorieProduit");
    }
}