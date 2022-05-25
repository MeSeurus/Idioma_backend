package ru.seurus.idioma.controller.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.stock.StockConsumables;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.buy.BuyConsumablesRepository;
import ru.seurus.idioma.repository.stock.StockConsumablesRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class StockConsumablesController {

    @Autowired
    private BuyConsumablesRepository buyConsumablesRepository;

    @Autowired
    private StockConsumablesRepository stockConsumablesRepository;

    @GetMapping("/stock_consumables")
    public List<StockConsumables> getAllStockConsumables() {
        return stockConsumablesRepository.findAll();
    }

    @PostMapping("/stock_consumables")
    public StockConsumables createStockConsumables(@RequestBody StockConsumables stockConsumables) {
        return stockConsumablesRepository.save(stockConsumables);
    }

    @GetMapping("/stock_consumables/{id}")
    public ResponseEntity<StockConsumables> getStockConsumablesById(@PathVariable Integer id) {
        StockConsumables stockConsumables = stockConsumablesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(stockConsumables);
    }

    @DeleteMapping("/stock_consumables/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStockConsumables(@PathVariable Integer id) {
        StockConsumables stockConsumables = stockConsumablesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        stockConsumablesRepository.delete(stockConsumables);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}