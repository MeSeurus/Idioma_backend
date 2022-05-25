package ru.seurus.idioma.controller.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.stock.StockPlasma;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.buy.BuyPlasmaRepository;
import ru.seurus.idioma.repository.stock.StockPlasmaRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class StockPlasmaController {

    @Autowired
    private BuyPlasmaRepository buyPlasmaRepository;

    @Autowired
    private StockPlasmaRepository stockPlasmaRepository;

    @GetMapping("/stock_plasma")
    public List<StockPlasma> getAllStockPlasma() {
        return stockPlasmaRepository.findAll();
    }

    @PostMapping("/stock_plasma")
    public StockPlasma createStockPlasma(@RequestBody StockPlasma stockPlasma) {
        return stockPlasmaRepository.save(stockPlasma);
    }

    @GetMapping("/stock_plasma/{id}")
    public ResponseEntity<StockPlasma> getStockPlasmaById(@PathVariable Integer id) {
        StockPlasma stockPlasma = stockPlasmaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(stockPlasma);
    }

    @DeleteMapping("/stock_plasma/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStockPlasma(@PathVariable Integer id) {
        StockPlasma stockPlasma = stockPlasmaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        stockPlasmaRepository.delete(stockPlasma);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}