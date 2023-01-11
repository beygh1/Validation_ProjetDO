package tn.esprit.rh.achat.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
@SpringBootTest
@AutoConfigureMockMvc
public class StockServiceTest {
    @Autowired
    private IStockService stockService;
    @MockBean
    private StockRepository repository;
    @Test
    public void retrieveStocks() {
        List<Stock> StockFixtures= Stream.of(new Stock("test1",50,1),new Stock("test2",20,1),new Stock("test3",40,5)).collect(Collectors.toList());
        when(repository.findAll()).thenReturn(StockFixtures);
        List<Stock> RetrievedStockList = stockService.retrieveAllStocks();
        Assertions.assertEquals(3, RetrievedStockList.size());
    }
    @Test
    public void addStock() {
        Stock stock = new Stock("test4",17,3);
        when(repository.save(stock)).thenReturn(stock);
        Assertions.assertEquals(stock, stockService.addStock(stock));
    }
    @Test
    public void deleteStock() {
        Stock stock = new Stock(1l,"test5",17,3,null);
        stockService.deleteStock(stock.getIdStock());
        verify(repository,times(1)).deleteById(stock.getIdStock());
    }
}