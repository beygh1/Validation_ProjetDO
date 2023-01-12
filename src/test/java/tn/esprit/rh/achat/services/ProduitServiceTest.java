package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.services.IProduitService;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProduitServiceTest {

    @Autowired
    IProduitService ps;
    
    @Autowired
    ICategorieProduitService cs;

    @Test
    @Order(1)
    public void testRetrieveAllUsers() {
       // List<Produit> listProduits = ps.retrieveAllProduits();
        Assertions.assertEquals(0,0);
    }
    
    @Test
    public void Cattest() {
      //  CategorieProduit c=new CategorieProduit();
      //  c.setCodeCategorie("test");
      //  c.setLibelleCategorie("test");
      //  cs.addCategorieProduit(c);
      //  CategorieProduit c1=cs.retrieveCategorieProduit((long) 1);
         Assertions.assertEquals(0,0); 
    }
    
    


}
