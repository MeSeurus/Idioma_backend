package ru.seurus.idioma.controller.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.stock.StockElectrics;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.buy.BuyElectricsRepository;
import ru.seurus.idioma.repository.stock.StockElectricsRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class StockElectricsController {

    @Autowired
    private BuyElectricsRepository buyElectricsRepository;

    @Autowired
    private StockElectricsRepository stockElectricsRepository;

    @GetMapping("/stock_electrics")
    public List<StockElectrics> getAllStockElectrics() {
        return stockElectricsRepository.findAll();
    }

    @PostMapping("/stock_electrics")
    public StockElectrics createStockElectrics(@RequestBody StockElectrics stockElectrics) {
        return stockElectricsRepository.save(stockElectrics);
    }

    @GetMapping("/stock_electrics/{id}")
    public ResponseEntity<StockElectrics> getStockElectricsById(@PathVariable Integer id) {
        StockElectrics stockElectrics = stockElectricsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(stockElectrics);
    }

    @DeleteMapping("/stock_electrics/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStockElectrics(@PathVariable Integer id) {
        StockElectrics stockElectrics = stockElectricsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        stockElectricsRepository.delete(stockElectrics);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
