package ru.seurus.idioma.controller.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.buy.BuyMetalRus;
import ru.seurus.idioma.entity.stock.StockMetalRus;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.buy.BuyMetalRusRepository;
import ru.seurus.idioma.repository.stock.StockMetalRusRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class StockMetalRusController {

    @Autowired
    private BuyMetalRusRepository buyMetalRusRepository;

    @Autowired
    private StockMetalRusRepository stockMetalRusRepository;

    @GetMapping("/stock_metal_rus")
    public List<StockMetalRus> getAllStockMetalRus() {
        return stockMetalRusRepository.findAll();
    }

    @PostMapping("/stock_metal_rus")
    public StockMetalRus createStockMetalRus(@RequestBody StockMetalRus stockMetalRus) {
        return stockMetalRusRepository.save(stockMetalRus);
    }

    @GetMapping("/stock_metal_rus/{id}")
    public ResponseEntity<StockMetalRus> getStockMetalRusById(@PathVariable Integer id) {
        StockMetalRus stockMetalRus = stockMetalRusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(stockMetalRus);
    }

    @DeleteMapping("/stock_metal_rus/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStockMetalRus(@PathVariable Integer id) {
        StockMetalRus stockMetalRus = stockMetalRusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        stockMetalRusRepository.delete(stockMetalRus);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/stock_metal_rus/{id}")
    public ResponseEntity<StockMetalRus> updateStockMetalRus(@PathVariable Integer id, @RequestBody StockMetalRus stockMetalRusUpdate) {
        StockMetalRus stockMetalRus = stockMetalRusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        stockMetalRus.setQuantityCatCurrent(stockMetalRusUpdate.getQuantityCatCurrent());
        stockMetalRus.setQuantityCurrent(stockMetalRusUpdate.getQuantityCurrent());

        StockMetalRus updatedStockMetalRus = stockMetalRusRepository.save(stockMetalRus);
        return ResponseEntity.ok(updatedStockMetalRus);
    }
}