package tn.esprit.rh.achat.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.rh.achat.entities.Produit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProduitImplTest {
	@Autowired
	IProduitService ProduitService;

	@Test
	public void testAddProduit() {
		List<Produit> Produits = ProduitService.retrieveAllProduits();
		int expected=Produits.size();
		Produit s = new Produit("id produit","Produit",23);
		Produit savedProduit= ProduitService.addProduit(s);

		assertEquals(expected+1, ProduitService.retrieveAllProduits().size());
		assertNotNull(savedProduit.getIdProduit());
		ProduitService.deleteProduit(savedProduit.getIdProduit());

	}

	@Test
	public void testAddProduitOptimized() {

		Produit s = new Produit("Produit code","Produit",23);
		Produit savedProduit= ProduitService.addProduit(s);
		assertNotNull(savedProduit.getIdProduit());
		assertSame("Produit code", savedProduit.getCodeProduit());
		assertNotEquals(0,savedProduit.getPrix());
		ProduitService.deleteProduit(savedProduit.getIdProduit());

	}


	@Test
	public void testDeleteProduit() {
		Produit s = new Produit("Produit code","Produit",23);
		Produit savedProduit= ProduitService.addProduit(s);
		ProduitService.deleteProduit(savedProduit.getIdProduit());
		assertNull(ProduitService.retrieveProduit(savedProduit.getIdProduit()));
	}


}
