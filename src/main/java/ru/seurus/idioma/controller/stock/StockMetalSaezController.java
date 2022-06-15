package ru.seurus.idioma.controller.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.stock.StockMetalRus;
import ru.seurus.idioma.entity.stock.StockMetalSaez;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.buy.BuyMetalSaezRepository;
import ru.seurus.idioma.repository.stock.StockMetalSaezRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class StockMetalSaezController {

    @Autowired
    private BuyMetalSaezRepository buyMetalSaezRepository;

    @Autowired
    private StockMetalSaezRepository stockMetalSaezRepository;

    @GetMapping("/stock_metal_saez")
    public List<StockMetalSaez> getAllStockMetalSaez() {
        return stockMetalSaezRepository.findAll();
    }

    @PostMapping("/stock_metal_saez")
    public StockMetalSaez createStockMetalSaez(@RequestBody StockMetalSaez stockMetalSaez) {
        return stockMetalSaezRepository.save(stockMetalSaez);
    }

    @GetMapping("/stock_metal_saez/{id}")
    public ResponseEntity<StockMetalSaez> getStockMetalSaezById(@PathVariable Integer id) {
        StockMetalSaez stockMetalSaez = stockMetalSaezRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(stockMetalSaez);
    }

    @DeleteMapping("/stock_metal_saez/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStockMetalSaez(@PathVariable Integer id) {
        StockMetalSaez stockMetalSaez = stockMetalSaezRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        stockMetalSaezRepository.delete(stockMetalSaez);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/stock_metal_saez/{id}")
    public ResponseEntity<StockMetalSaez> updateStockMetalSaez(@PathVariable Integer id, @RequestBody StockMetalSaez stockMetalSaezUpdate) {
        StockMetalSaez stockMetalSaez = stockMetalSaezRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        stockMetalSaez.setQuantityCurrent(stockMetalSaezUpdate.getQuantityCurrent());

        StockMetalSaez updatedStockMetalSaez = stockMetalSaezRepository.save(stockMetalSaez);
        return ResponseEntity.ok(updatedStockMetalSaez);
    }
}
