package ru.seurus.idioma.controller.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.stock.StockImportParts;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.buy.BuyImportPartsRepository;
import ru.seurus.idioma.repository.stock.StockImportPartsRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class StockImportPartsController {

    @Autowired
    private BuyImportPartsRepository buyImportPartsRepository;

    @Autowired
    private StockImportPartsRepository stockImportPartsRepository;

    @GetMapping("/stock_import_parts")
    public List<StockImportParts> getAllStockImportParts() {
        return stockImportPartsRepository.findAll();
    }

    @PostMapping("/stock_import_parts")
    public StockImportParts createStockImportParts(@RequestBody StockImportParts stockImportParts) {
        return stockImportPartsRepository.save(stockImportParts);
    }

    @GetMapping("/stock_import_parts/{id}")
    public ResponseEntity<StockImportParts> getStockImportPartsById(@PathVariable Integer id) {
        StockImportParts stockImportParts = stockImportPartsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(stockImportParts);
    }

    @DeleteMapping("/stock_import_parts/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStockImportParts(@PathVariable Integer id) {
        StockImportParts stockImportParts = stockImportPartsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        stockImportPartsRepository.delete(stockImportParts);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
