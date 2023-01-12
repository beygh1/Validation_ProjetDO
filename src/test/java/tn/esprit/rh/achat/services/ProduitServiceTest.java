package tn.esprit.rh.achat.services;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import static org.mockito.Mockito.when;
import java.util.List;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProduitServiceTest {
    @Autowired
    private IProduitService ps;
    @MockBean
    private ProduitRepository produitRepository;
    @Test
    public void should_Return_All_Produit() {
        List<Produit> ProduitData = Stream.of(new Produit(123L, "a", "b", (float) 12.4, null, null, null, null, null),
                new Produit(124L, "a", "b", (float) 12.4, null, null, null, null, null)).collect(Collectors.toList());
        when(produitRepository.findAll()).thenReturn(ProduitData);
        List<Produit> RetrievedStockList = ps.retrieveAllProduits();
        Assertions.assertEquals(2, RetrievedStockList.size());
    }
    @Test
    public void should_Add_Produit() {
        Produit myProduit = new Produit(123L, "a", "b", (float) 12.4, null, null, null, null, null);
        when(produitRepository.save(myProduit)).thenReturn(myProduit);
        Assertions.assertEquals(myProduit, ps.addProduit(myProduit));
    }
}