package ru.seurus.idioma.controller.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.stock.StockMetalImport;
import ru.seurus.idioma.entity.stock.StockMetalRus;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.buy.BuyMetalImportRepository;
import ru.seurus.idioma.repository.stock.StockMetalImportRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class StockMetalImportController {

    @Autowired
    private BuyMetalImportRepository buyMetalImportRepository;

    @Autowired
    private StockMetalImportRepository stockMetalImportRepository;

    @GetMapping("/stock_metal_import")
    public List<StockMetalImport> getAllStockMetalImport() {
        return stockMetalImportRepository.findAll();
    }

    @PostMapping("/stock_metal_import")
    public StockMetalImport createStockMetalImport(@RequestBody StockMetalImport stockMetalImport) {
        return stockMetalImportRepository.save(stockMetalImport);
    }

    @GetMapping("/stock_metal_import/{id}")
    public ResponseEntity<StockMetalImport> getStockMetalImportById(@PathVariable Integer id) {
        StockMetalImport stockMetalImport = stockMetalImportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(stockMetalImport);
    }

    @DeleteMapping("/stock_metal_import/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStockMetalImport(@PathVariable Integer id) {
        StockMetalImport stockMetalImport = stockMetalImportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        stockMetalImportRepository.delete(stockMetalImport);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/stock_metal_import/{id}")
    public ResponseEntity<StockMetalImport> updateStockMetalImport(@PathVariable Integer id, @RequestBody StockMetalImport stockMetalImportUpdate) {
        StockMetalImport stockMetalImport = stockMetalImportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        stockMetalImport.setQuantityCatCurrent(stockMetalImportUpdate.getQuantityCatCurrent());
        stockMetalImport.setQuantityCurrent(stockMetalImportUpdate.getQuantityCurrent());

        StockMetalImport updatedStockMetalImport = stockMetalImportRepository.save(stockMetalImport);
        return ResponseEntity.ok(updatedStockMetalImport);
    }
}
